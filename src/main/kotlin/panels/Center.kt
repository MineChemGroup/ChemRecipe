package main.kotlin.panels

import main.kotlin.misc.Inst
import java.awt.*
import javax.swing.JPanel
import javax.swing.JTabbedPane

class Center(val jPanel: JPanel = JPanel()) {

    val tabbed = JTabbedPane()

    fun init() {
        tabbed.addTab("Synthesizer", Inst.sEditor.jPanel)
        tabbed.addTab("Decomposer", Inst.cEditor.jPanel)
        tabbed.font = Inst.font.deriveFont(15f)
        jPanel.layout = FlowLayout()
        jPanel.add(tabbed)
    }
}