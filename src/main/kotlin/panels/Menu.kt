package panels

import main.kotlin.misc.Inst
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem

class Menu(val bar: JMenuBar = JMenuBar()){

    val author = JMenuItem("Author").apply { addActionListener(Inst.buttonActions)}
    val github = JMenuItem("Github Repository").apply { addActionListener(Inst.buttonActions)}

    val light = JMenuItem("Light").apply { addActionListener(Inst.buttonActions)}
    val solarized = JMenuItem("Solarized").apply { addActionListener(Inst.buttonActions) }
    val deepocean = JMenuItem("Deep Ocean").apply { addActionListener(Inst.buttonActions) }
    val nord = JMenuItem("Nord").apply { addActionListener(Inst.buttonActions) }
    val dark = JMenuItem("Darcula").apply { addActionListener(Inst.buttonActions) }

    fun init(){
        val theme: JMenu = JMenu("Theme").apply { add(light); add(solarized); add(nord); add(deepocean); add(dark)}
        val about = JMenu("About").apply { add(author); add(github) }

        bar.apply { add(about); add(theme) }
    }
}