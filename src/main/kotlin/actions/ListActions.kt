package main.kotlin.actions

import actions.ButtonActions
import actions.SEditorActions
import kotlinx.coroutines.*
import misc.Inst
import misc.Inst.copy
import main.kotlin.misc.Saver
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

    var current = -1
    var justRemoved = false

    override fun valueChanged(e: ListSelectionEvent?) {
        runBlocking {
            launch {
                delay(100)

                if (e?.valueIsAdjusting == true) {
                    return@launch
                    //even fires twice for some reason
                }

                if (Inst.right.demoList.size() == 0) {
                    current = -1
                    return@launch
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
                var new = if (e?.firstIndex != current)
                    e?.firstIndex!!
                else
                    e.lastIndex

                if (ButtonActions.removing) {
                    println("stopped due to removing")
                    return@launch
                } else
                    println("not stopped due to removing")

                /*
        if (current != new) {
            if (Inst.right.demoList.size() >= current + 1) {
                Saver.save(current)
            }else {
                return
            }
        }
        */
                if (current != new) {
                    if (justRemoved){

                        current = Inst.right.list.selectedIndex
                        new = Inst.right.list.selectedIndex

                        justRemoved = false
                    } else {
                        if (current != -1) {
                            Saver.save(current)
                            println("saving $current")

                            Inst.sEditor.reset()
                            Inst.cEditor.reset()
                        }
                        current = new
                    }
                } else {
                    justRemoved = false
                }

                println("new: $current")
                println(" ")

                //this loads the new recipe
                println("file>>> " + Inst.loader.recipeFolder.toString() + "/" + Inst.right.demoList[current] + ".chemrecipe")
                val file = File(Inst.loader.recipeFolder.toString() + "/" + Inst.right.demoList[current] + ".chemrecipe")

                if (!file.exists()) {
                    withContext(Dispatchers.IO) {
                        file.createNewFile()
                    }
                    return@launch
                }

                try {
                    withContext(Dispatchers.IO) {
                        BufferedReader(FileReader(file)).use { br ->
                            br.lines().forEach {

                                if (it == "sEditor" || it == "cEditor")
                                    return@forEach


                                val splot = it.split(":")

                                if (splot.size == 1)
                                    return@forEach

                                val label: JLabel = Inst.left.getAsset(splot[1])?.copy() ?: return@forEach

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
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
}