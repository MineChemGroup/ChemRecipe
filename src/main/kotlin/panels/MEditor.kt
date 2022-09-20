package panels

import misc.Inst
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.Font
import javax.swing.JButton
import javax.swing.JPanel

class MEditor(val jPanel: JPanel = JPanel()) {

    val createnew = JButton("Create New")

    fun init(){
        createnew.addActionListener(Inst.actions)
        jPanel.layout = FlowLayout()
        jPanel.preferredSize = Dimension(340,800)
        jPanel.add(createnew)
        createnew.apply { preferredSize = Dimension(150,75); font = Inst.font }

        jPanel.revalidate()
    }
}