package panels

import misc.Inst
import org.intellij.lang.annotations.JdkConstants.TabLayoutPolicy
import java.awt.*
import javax.swing.BoxLayout
import javax.swing.JPanel
import javax.swing.JTabbedPane
import javax.swing.plaf.basic.BasicTabbedPaneUI.TabbedPaneLayout

class Center(val jPanel: JPanel = JPanel()) {

    var activetab = 0

    fun init() {
        val tabbed = JTabbedPane()
        tabbed.addTab("Synthesizer", Inst.sEditor.jPanel)
        tabbed.addTab("Decomposer", Inst.cEditor.jPanel)
        tabbed.font = Inst.font.deriveFont(15f)
        jPanel.layout = FlowLayout()
        jPanel.add(tabbed)
    }
}