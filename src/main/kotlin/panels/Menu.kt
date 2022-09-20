package panels

import misc.Inst
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem

class Menu(val bar: JMenuBar = JMenuBar()){

    val light = JMenuItem("Light").apply { addActionListener(Inst.actions)}
    val solarized = JMenuItem("Solarized").apply { addActionListener(Inst.actions) }
    val deepocean = JMenuItem("Deep Ocean").apply { addActionListener(Inst.actions) }
    val nord = JMenuItem("Nord").apply { addActionListener(Inst.actions) }
    val dark = JMenuItem("Darcula").apply { addActionListener(Inst.actions) }

    fun init(){
        val file = JMenu("File")
        val theme: JMenu = JMenu("Theme").apply { add(light); add(solarized); add(nord); add(deepocean); add(dark)}

        bar.apply { add(file); add(theme) }
    }
}