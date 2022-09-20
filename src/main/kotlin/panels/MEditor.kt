package panels

import misc.Inst
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.Font
import java.awt.GridLayout
import java.io.File
import javax.imageio.ImageIO
import javax.swing.*

class MEditor(val jPanel: JPanel = JPanel()) {

    val createnew = JButton("Create New").apply { alignmentX = 0.5f}

    val listPanel = arrayListOf<JPanel>()
    val listLabel = arrayListOf<JLabel>()
    val listSpinner = arrayListOf<JSpinner>()

    fun init(){
        createnew.addActionListener(Inst.actions)
        jPanel.layout = BoxLayout(jPanel, BoxLayout.PAGE_AXIS)
        //jPanel.preferredSize = Dimension(400,800)
        createnew.apply { preferredSize = Dimension(200,75); font = Inst.font }
        createnew.minimumSize = Dimension(200,75)
        jPanel.add(createnew)
        jPanel.add(Box.createVerticalStrut(20))

        val centerpanel = JLabel(ImageIcon(File(Inst.loader.baseFolder.path + "/3x3.png").path)).apply { minimumSize = Dimension(160,160); alignmentX = 0.5f; alignmentY = 0.2f}
        centerpanel.layout = GridLayout(3,3)
        /*
        for (i in 1..9){
            val label = JLabel(ImageIcon(Inst.loader.ws.path))
            listLabel.add(label)
            val panel = JPanel(FlowLayout())
            panel.add(label)
            listPanel.add(panel)
            centerpanel.add(panel)
        }

         */
        jPanel.add(centerpanel)
        jPanel.add(Box.createVerticalStrut(20))
        val arrow = JLabel(ImageIcon(File(Inst.loader.baseFolder.path + "/arrow.png").path)).apply { alignmentX = 0.5f }
        jPanel.add(arrow)

        jPanel.revalidate()
    }
}