import com.formdev.flatlaf.FlatDarculaLaf
import com.formdev.flatlaf.FlatDarkLaf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import misc.Inst
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.WindowConstants


suspend fun main(args: Array<String>){

    FlatDarculaLaf.setup()

    Inst.jframe.isVisible = true
    Inst.jframe.size = Dimension(1000, 600)
    Inst.jframe.minimumSize = Dimension(800,480)
    Inst.jframe.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE;

    Inst.jframe.layout = BorderLayout()

    coroutineScope {
        launch (Dispatchers.IO) {
            Inst.loader.init()
            Inst.loader.paste()
            Inst.loader.load()

            Inst.left.init()
            Inst.right.init()

            Inst.jframe.revalidate()
        }
        launch {
            Inst.jframe.jMenuBar = Inst.menu.bar
            Inst.jframe.add(Inst.mEditor.jPanel, BorderLayout.CENTER)
            Inst.jframe.add(Inst.left.jPanel, BorderLayout.WEST)
            Inst.jframe.add(Inst.right.jPanel, BorderLayout.EAST)
            Inst.jframe.isVisible = true
        }
    }
}
