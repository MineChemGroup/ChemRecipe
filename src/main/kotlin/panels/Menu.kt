package panels

import misc.Inst
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem

class Menu(val bar: JMenuBar = JMenuBar()){

    val light = JMenuItem("Light").apply { addActionListener(Inst.buttonActions)}
    val solarized = JMenuItem("Solarized").apply { addActionListener(Inst.buttonActions) }
    val deepocean = JMenuItem("Deep Ocean").apply { addActionListener(Inst.buttonActions) }
    val nord = JMenuItem("Nord").apply { addActionListener(Inst.buttonActions) }
    val dark = JMenuItem("Darcula").apply { addActionListener(Inst.buttonActions) }

    fun init(){
        val file = JMenu("File")
        val theme: JMenu = JMenu("Theme").apply { add(light); add(solarized); add(nord); add(deepocean); add(dark)}

        bar.apply { add(file); add(theme) }
    }
}