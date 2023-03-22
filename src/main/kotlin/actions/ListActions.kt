package main.kotlin.actions

import actions.CEditorActions
import actions.SEditorActions
import misc.Inst
import misc.Inst.copy
import misc.Saver
import transfer.CEditorHandler
import transfer.SEditorHandler
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import javax.swing.JLabel
import javax.swing.event.ListSelectionEvent
import javax.swing.event.ListSelectionListener


class ListActions : ListSelectionListener {

    private var current = 0

    override fun valueChanged(e: ListSelectionEvent?) {
        if (e?.valueIsAdjusting == true) {
            return
            //even fires twice for some reason
        }

        if (Inst.right.demoList.size() == 0) {
            current = 0
            return
        }

        println("current: $current")

        /*
        if (Inst.right.demoList.size() < current + 1){
            current = if (Inst.right.list.selectedIndex != -1)
                Inst.right.list.selectedIndex
            else
                0

            return
        }
        */

        //this saves previous recipe and resets editors
        val new = if (e?.firstIndex != current)
            e?.firstIndex!!
        else
            e.lastIndex

        /*
        if (current != new) {
            if (Inst.right.demoList.size() >= current + 1) {
                Saver.save(current)
            }else {
                return
            }
        }
        */
        if (current != new)
            Saver.save(current)

        Inst.sEditor.reset()
        Inst.cEditor.reset()

        current = new

        println("new: $current")
        println(" ")

        //this loads the new recipe
        val file = File(Inst.loader.recipeFolder.toString() + "/" + Inst.right.demoList[current] + ".chemrecipe")

        if (!file.exists()) {
            file.createNewFile()
            return
        }

        try {
            BufferedReader(FileReader(file)).use { br ->
                br.lines().forEach {

                    if (it == "sEditor" || it == "cEditor")
                        return@forEach


                    val splot = it.split(":")

                    if (splot.size == 1)
                        return@forEach

                    val label : JLabel = Inst.left.getAsset(splot[1])?.copy() ?: return@forEach

                    if (splot[0] == "S"){
                        label.transferHandler = SEditorHandler()
                        label.addMouseListener(SEditorActions())
                        Inst.sEditor.add(label, 10, splot[2].toInt())
                        return@forEach
                    }
                    if (splot[0] == "C"){
                        label.transferHandler = CEditorHandler()
                        label.addMouseListener(CEditorActions())
                        Inst.cEditor.add(label, 0, splot[2].toInt(), 0)
                        return@forEach
                    }

                    val num = splot[0].toInt()

                    if (num <= 9) {
                        label.transferHandler = SEditorHandler()
                        label.addMouseListener(SEditorActions())
                        Inst.sEditor.add(label, num, splot[2].toInt())
                        return@forEach
                    } else if (num > 9){
                        label.transferHandler = CEditorHandler()
                        label.addMouseListener(CEditorActions())
                        Inst.cEditor.add(label, num, splot[2].toInt(), splot[3].toInt())
                        return@forEach
                    }

                }
                Inst.refresh()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}