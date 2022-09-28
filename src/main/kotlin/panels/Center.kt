package panels

import misc.Inst
import java.awt.*
import javax.swing.JPanel
import javax.swing.JTabbedPane

class Center(val jPanel: JPanel = JPanel()) {

    fun init() {
        val tabbed = JTabbedPane()
        tabbed.addTab("Synthesizer", Inst.sEditor.jPanel)
        tabbed.addTab("Decomposer", Inst.cEditor.jPanel)
        tabbed.font = Inst.font.deriveFont(15f)
        jPanel.layout = FlowLayout()
        jPanel.add(tabbed)
    }
}