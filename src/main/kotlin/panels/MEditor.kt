package panels

import misc.Inst
import misc.MEditorActions
import java.awt.*
import java.io.File
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
        createnew.apply { preferredSize = Dimension(175,50); font = Inst.font }
        createnew.minimumSize = Dimension(175,50)
        createnew.maximumSize = Dimension(175,50)
        jPanel.add(createnew)

        val layeredpane = JLayeredPane()

        val jLabel = JLabel(ImageIcon(File(Inst.loader.baseFolder.path + "/3x3.png").path))/*.apply { minimumSize = Dimension(160,160); alignmentX = 0.5f; alignmentY = 0.2f}*/
        jLabel.setBounds(0,0,160,160)
        layeredpane.add(jLabel, JLayeredPane.DEFAULT_LAYER)

        val diff = 52
        val oX = 12
        val oY = 12
        var x = 0
        var y = 0
        for (i in 1..9){

            val label = JLabel()
            label.background = Color(139,139,139)
            listLabel.add(label)
            label.addMouseListener(MEditorActions())
            label.isVisible = true
            label.isOpaque = true
            layeredpane.add(label, Integer(i))

            if (x == diff*3) {
                x = 0
                y += diff
            }
            label.setBounds(x+oX, y+oY, 32,32)
            x += diff
        }
        layeredpane.isVisible = true
        val secondpanel = JPanel(GridLayout(1,1)).apply { alignmentX = 0.5f }
        secondpanel.add(layeredpane)
        secondpanel.alignmentX = 0.5f
        secondpanel.minimumSize = Dimension(160,160)
        secondpanel.maximumSize = Dimension(160,160)
        secondpanel.preferredSize = Dimension(160,160)

        val mainPanel = JPanel()
        mainPanel.layout = BoxLayout(mainPanel, BoxLayout.PAGE_AXIS)
        mainPanel.add(Box.createVerticalStrut(40))
        mainPanel.add(secondpanel)
        mainPanel.add(Box.createVerticalStrut(5))
        val arrow = JLabel(ImageIcon(File(Inst.loader.baseFolder.path + "/arrow.png").path)).apply { alignmentX = 0.5f }
        mainPanel.add(arrow)

        mainPanel.add(Box.createVerticalStrut(5))

        val layeredpane1 = JLayeredPane()
        layeredpane1.setBounds(85,20,32,32)
        val resultLabel = JLabel()
        resultLabel.background = Color(139,139,139)
        listLabel.add(resultLabel)
        resultLabel.addMouseListener(MEditorActions())
        resultLabel.isVisible = true
        resultLabel.isOpaque = true
        resultLabel.setBounds(6,6,32,32)
        val oneslot = JLabel(ImageIcon(File(Inst.loader.baseFolder.path + "/oneslot.png").path)).apply { alignmentX = Component.CENTER_ALIGNMENT }
        oneslot.setBounds(0,0,44,44)
        //oneslot.size = Dimension(45,)
        layeredpane1.add(oneslot, JLayeredPane.DEFAULT_LAYER)
        layeredpane1.add(resultLabel, Integer(1))
        val thirdpanel = JPanel(GridLayout(1,1)).apply { alignmentX = Component.CENTER_ALIGNMENT }
        thirdpanel.add(layeredpane1)
        thirdpanel.minimumSize = Dimension(44,44)
        thirdpanel.maximumSize = Dimension(44,44)
        thirdpanel.preferredSize = Dimension(44,44)
        thirdpanel.alignmentX = 0.5f

        mainPanel.add(thirdpanel)
        mainPanel.add(Box.createVerticalStrut(150))

        jPanel.add(mainPanel)
        jPanel.revalidate()
    }
}