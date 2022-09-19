package panels

import java.awt.BorderLayout
import javax.swing.JButton
import javax.swing.JPanel

class MEditor(val jPanel: JPanel = JPanel()) {

    val createnew = JButton("Create New")

    fun init(){
        jPanel.add(createnew, BorderLayout.SOUTH)
    }
}