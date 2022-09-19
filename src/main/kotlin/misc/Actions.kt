package misc

import com.formdev.flatlaf.FlatDarculaLaf
import com.formdev.flatlaf.FlatLightLaf
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.SwingUtilities

class Actions : ActionListener {
    override fun actionPerformed(e: ActionEvent?) {
        when(e?.source) {
            Inst.menu.dark -> {
                FlatDarculaLaf.setup();
                Inst.refresh() }
            Inst.menu.light -> {
                FlatLightLaf.setup()
                Inst.refresh() }
            Inst.mEditor.createnew -> {

            }
        }
    }
}