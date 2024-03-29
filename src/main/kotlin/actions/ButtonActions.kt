package main.kotlin.actions

import com.formdev.flatlaf.FlatDarculaLaf
import com.formdev.flatlaf.FlatIntelliJLaf
import com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatSolarizedLightIJTheme
import kotlinx.coroutines.*
import main.kotlin.misc.Inst
import main.kotlin.misc.Inst.copy
import main.kotlin.misc.Inst.copyHandler
import main.kotlin.misc.Saver
import main.kotlin.tooltip.CustomLabel
import java.awt.Desktop
import java.awt.Dimension
import java.awt.Rectangle
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.io.File
import java.net.URL
import javax.swing.JLabel
import javax.swing.JOptionPane
import javax.swing.JSpinner


class ButtonActions : ActionListener {
    override fun actionPerformed(e: ActionEvent) {
        when(e.source) {
            Inst.menu.author -> {
                Desktop.getDesktop().browse(URL("https://github.com/justADeni").toURI())
            }
            Inst.menu.github -> {
                Desktop.getDesktop().browse(URL("https://github.com/justADeni/ChemRecipe").toURI())
            }
            Inst.menu.light -> {
                FlatIntelliJLaf.setup()
            }
            Inst.menu.solarized -> {
                FlatSolarizedLightIJTheme.setup()
            }
            Inst.menu.nord -> {
                FlatNordIJTheme.setup()
            }
            Inst.menu.deepocean -> {
                FlatGradiantoDeepOceanIJTheme.setup()
            }
            Inst.menu.dark -> {
                FlatDarculaLaf.setup()
            }

            Inst.right.save -> {
                Saver.save()
            }
            Inst.right.createnew -> {
                //if (Inst.right.list.selectedIndex != -1)
                    //Saver.save()

                //Inst.cEditor.reset()
                //Inst.sEditor.reset()

                var recipeNum = 0
                for (item in Inst.right.demoList.elements())
                    if (item.contains("New Recipe"))
                        recipeNum++

                Inst.right.demoList.addElement("New Recipe #$recipeNum")

                //println("demoList.size() >>> " + Inst.right.demoList.size)
                Inst.right.list.selectedIndex = Inst.right.demoList.size - 1

                Saver.save()
            }
            Inst.right.remove -> {
                removing = true

                //println("1")

                val listIndex = Inst.right.list.selectedIndex
                if (listIndex != -1) {
                    //println("2")
                    val file = File(Inst.loader.recipeFolder.toString() + "/" + Inst.right.demoList[listIndex] + ".chemrecipe")

                    Saver.compoundDeleteCheck(file.toPath())

                    if (file.exists()) {
                        //println("3")
                        file.delete()
                    }
                    //Inst.right.demoList.remove(listIndex)
                    //Inst.right.list.remove(listIndex)

                    Saver.reloadall()

                    Inst.listActions.justRemoved = true
                    Inst.listActions.current = -1

                    runBlocking {
                        launch {
                            delay(220)
                            removing = false
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a recipe")
                }

                Inst.cEditor.reset()
                Inst.sEditor.reset()

                //println("4")

                /*
                Saver.reloadall()

                Inst.listActions.justRemoved = true
                Inst.listActions.current = -1

                runBlocking {
                    launch {
                        delay(220)
                        removing = false
                    }
                }
                */
            }
            Inst.right.openfolder -> {
                Desktop.getDesktop().open(Inst.loader.recipeFolder)
            }
            Inst.sEditor.mirror -> {
                Inst.cEditor.reset()

                val labelMap = hashMapOf<CustomLabel, Int>()

                for (s in Inst.sEditor.upperlayeredpane.getComponentsInLayer(21)){
                    val spinner = s as JSpinner
                    val id = spinner.getClientProperty("connected") as Int - 10

                    val label = (Inst.sEditor.upperlayeredpane.getComponentsInLayer(id+10)[0] as CustomLabel).copyHandler()

                    //println("id $id")
                    var unduplicate = false
                    for (otherLabel in labelMap.keys){
                        if (otherLabel.info == label.info){
                            //println("duplicate found")
                            unduplicate = true

                            for (i in Inst.cEditor.listpanel.components) {
                                val comps = (i as JLabel).components
                                if (comps[0] == otherLabel) {
                                    labelMap[otherLabel] = labelMap[otherLabel]!! + (spinner.value as Int)
                                    (comps[1] as JSpinner).value = labelMap[otherLabel]
                                    break
                                }
                            }

                            break
                        }
                    }

                    if (unduplicate)
                        continue

                    labelMap[label] = spinner.value as Int

                    label.isVisible = true
                    label.bounds = Rectangle(0,0,32,32)
                    label.preferredSize = Dimension(32,32)
                    label.size = Dimension(32,32)

                    Inst.cEditor.add(label)
                    for (i in Inst.cEditor.listpanel.components) {
                        val comps = (i as JLabel).components
                        if (comps[0] == label) {
                            (comps[1] as JSpinner).value = spinner.value
                            break
                        }
                    }
                }
                if (Inst.sEditor.lowerlayeredpane.getComponentCountInLayer(21) > 0){
                    val spinner = Inst.sEditor.lowerlayeredpane.getComponentsInLayer(21)[0] as JSpinner
                    val label = (Inst.sEditor.lowerlayeredpane.getComponentsInLayer(20)[0] as CustomLabel).copyHandler()

                    label.bounds = Rectangle(label.bounds.x-5, label.bounds.y, 32, 32)
                    Inst.cEditor.layeredpane1.add(label, Integer(20))
                    Inst.cEditor.layeredpane1.setLayer(label, 20)
                    val sspinner = JSpinner()
                    sspinner.setBounds(label.bounds.x - 22, label.bounds.y + 28, 60, 32)
                    sspinner.value = spinner.value
                    Inst.cEditor.layeredpane1.add(sspinner, Integer(21))
                    Inst.cEditor.layeredpane1.setLayer(sspinner, 21)
                    label.addMouseWheelListener { e ->
                        val s = Inst.cEditor.layeredpane1.getComponentsInLayer(21)[0] as JSpinner
                        s.value = (s.value as Int + -e.wheelRotation)
                    }
                    sspinner.addChangeListener { e ->
                        val num: Int = (e.source as JSpinner).value as Int
                        if (num > 64) {
                            (e.source as JSpinner).value = 64
                        } else if (num < 1) {
                            Inst.cEditor.layeredpane1.remove(Inst.cEditor.layeredpane1.getComponentsInLayer(20)[0])
                            Inst.cEditor.layeredpane1.remove(e.source as JSpinner)
                            Inst.refresh()
                        }
                    }
                }
            }
        }
        Inst.refresh()
    }

    companion object{
        var removing = false
    }
}