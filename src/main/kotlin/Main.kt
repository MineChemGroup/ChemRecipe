package main.kotlin

import com.formdev.flatlaf.FlatDarculaLaf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import misc.Inst
import misc.Saver
import java.awt.*
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import java.awt.event.MouseEvent
import java.io.File
import javax.swing.JComponent
import javax.swing.JScrollPane
import javax.swing.WindowConstants


suspend fun main(args: Array<String>){

    FlatDarculaLaf.setup()

    Inst.jframe.size = Dimension(1000, 645)
    Inst.jframe.minimumSize = Dimension(944,645)
    Inst.jframe.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE;

    Inst.jframe.layout = GridLayout(1,3)

    coroutineScope {
        launch (Dispatchers.IO) {
            Inst.loader.init()
            Inst.loader.paste()
            Inst.loader.load()
            Inst.font = Font.createFont(Font.TRUETYPE_FONT, File(Inst.loader.baseFolder.path + "/mcfont.ttf")).deriveFont(20f)
            val ge = GraphicsEnvironment.getLocalGraphicsEnvironment()
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, File(Inst.loader.baseFolder.path + "/mcfont.ttf")))

            //delay(200)

            Inst.left.init()
            Inst.right.init()

            Inst.menu.init()
            Inst.sEditor.init()
            Inst.cEditor.init()
            Inst.center.init()

            Inst.jframe.revalidate()

            Saver.reloadall()
        }
        launch {
            Inst.jframe.jMenuBar = Inst.menu.bar
            Inst.jframe.add(Inst.left.jPanel)
            Inst.jframe.add(Inst.center.jPanel)
            Inst.jframe.add(Inst.right.jPanel)
            Inst.jframe.isVisible = true
        }
    }
}
