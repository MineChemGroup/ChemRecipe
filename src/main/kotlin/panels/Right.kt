package panels

import misc.Actions
import misc.Inst
import java.awt.BorderLayout
import java.awt.Component
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.event.ActionListener
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JPanel

class Right(val jPanel: JPanel = JPanel()){

    val openfolder = JButton("Open Folder").apply { alignmentX = 0.5f}

    fun init(){
        openfolder.addActionListener(Inst.actions)
        jPanel.layout = BoxLayout(jPanel, BoxLayout.PAGE_AXIS)
        jPanel.add(openfolder)
        openfolder.apply { preferredSize = Dimension(200,75); font = Inst.font }

        jPanel.revalidate()
    }
}