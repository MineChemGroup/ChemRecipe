package actions

import com.formdev.flatlaf.FlatDarculaLaf
import com.formdev.flatlaf.FlatIntelliJLaf
import com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatSolarizedLightIJTheme
import misc.Inst
import misc.Inst.copy
import misc.Saver
import java.awt.Desktop
import java.awt.Dimension
import java.awt.Rectangle
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.io.File
import java.net.URL
import javax.swing.JLabel
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
                FlatDarculaLaf.setup();
            }

            Inst.right.save -> {
                Saver.save()
            }
            Inst.right.createnew -> {
                Inst.cEditor.reset()
                Inst.sEditor.reset()

                Inst.right.demoList.addElement("New Recipe")
                Inst.right.list.selectedIndex = Inst.right.demoList.size() - 1
            }
            Inst.right.remove -> {
                val listIndex = Inst.right.list.selectedIndex
                if (listIndex != -1) {
                    val file = File(Inst.loader.recipeFolder.toString() + "/" + Inst.right.demoList[listIndex] + ".chemrecipe")
                    if (file.exists())
                        file.delete()
                    Inst.right.demoList.remove(listIndex)
                }
                Inst.cEditor.reset()
                Inst.sEditor.reset()
            }
            Inst.right.openfolder -> {
                Desktop.getDesktop().open(Inst.loader.recipeFolder)
            }
            Inst.sEditor.mirror -> {
                Inst.cEditor.reset()
                for (s in Inst.sEditor.upperlayeredpane.getComponentsInLayer(21)){
                    val spinner = s as JSpinner
                    val id = spinner.getClientProperty("connected") as Int - 10

                    val label = (Inst.sEditor.upperlayeredpane.getComponentsInLayer(id+10)[0] as JLabel).copy()
                    label.isVisible = true
                    label.bounds = Rectangle(0,0,32,32)
                    label.preferredSize = Dimension(32,32)
                    label.size = Dimension(32,32)
                    Inst.cEditor.add(label)
                    for (i in Inst.cEditor.listpanel.components) {
                        val comps = (i as JLabel).components
                        if (comps[0] == label) {
                            (comps[1] as JSpinner).value = spinner.value
                        }
                    }
                }
                if (Inst.sEditor.lowerlayeredpane.getComponentCountInLayer(21) > 0){
                    val spinner = Inst.sEditor.lowerlayeredpane.getComponentsInLayer(21)[0] as JSpinner
                    val label = (Inst.sEditor.lowerlayeredpane.getComponentsInLayer(20)[0] as JLabel).copy()

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
}