package main.kotlin.actions

import kotlinx.coroutines.*
import main.kotlin.misc.Inst
import main.kotlin.misc.Inst.copyHandler
import main.kotlin.misc.Saver
import main.kotlin.tooltip.CustomLabel
import transfer.CEditorHandler
import main.kotlin.transfer.SEditorHandler
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

import javax.swing.event.ListSelectionEvent
import javax.swing.event.ListSelectionListener


class ListActions : ListSelectionListener {

    var current = -1
    var justRemoved = false

    override fun valueChanged(e: ListSelectionEvent?) {

        if (current == -1){
            Inst.sEditor.reset()
            Inst.cEditor.reset()
        }

        runBlocking {
            launch {
                delay(10)

                if (e?.valueIsAdjusting == true) {
                    return@launch
                    //even fires twice for some reason
                }

                if (Inst.right.demoList.size() == 0) {
                    current = -1
                    return@launch
                }

                //println("current: $current")


                //this saves previous recipe and resets editors
                var new = if (e?.firstIndex != current)
                    e?.firstIndex!!
                else
                    e.lastIndex

                if (ButtonActions.removing) //{
                    //println("stopped due to removing")
                    return@launch
                //} else
                    //println("not stopped due to removing")


                if (current != new) {
                    if (justRemoved){

                        current = Inst.right.list.selectedIndex
                        new = Inst.right.list.selectedIndex

                        justRemoved = false
                    } else {
                        if (current != -1) {
                            Saver.save(current)
                            //println("saving $current")

                            Inst.sEditor.reset()
                            Inst.cEditor.reset()
                        }
                        current = new

                        loadRecipe()
                    }
                } else {
                    justRemoved = false
                }

                //println("new: $current")
                //println(" ")

                //this loads the new recipe
                //println("file>>> " + Inst.loader.recipeFolder.toString() + "/" + Inst.right.demoList[current] + ".chemrecipe")

            }
        }
    }

    fun loadRecipe(){
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

                    val label: CustomLabel = Inst.left.getAsset(splot[1])?.copyHandler() ?: return@forEach

                    if (splot[0] == "S") {
                        label.transferHandler = SEditorHandler()
                        label.addMouseListener(SEditorActions())
                        Inst.sEditor.add(label, 10, splot[2].toInt())
                        return@forEach
                    }
                    if (splot[0] == "C") {
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
                    } else if (num > 9) {
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