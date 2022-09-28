package panels

import misc.Inst
import actions.SEditorActions
import transfer.SEditorHandler
import java.awt.*
import java.io.File
import javax.swing.*

class SEditor(val jPanel: JPanel = JPanel()) {

    val listLabel = arrayListOf<JLabel>()

    val mirror = JButton("Mirror recipe").apply { alignmentX = 0.5f }

    lateinit var upperlayeredpane : JLayeredPane
    lateinit var lowerlayeredpane : JLayeredPane

    //TODO: add spinner
    fun add(label : JLabel){
        for(i in listLabel){
            if (i.mousePosition != null){
                val id = i.getClientProperty("number") as Int
                label.bounds = Rectangle(i.bounds.x+10, i.bounds.y, 32,32)

                /**
                 * I don't have even the slightest idea
                 * on WHY THE FUCK does this need to be
                 * like this. I tried a million other
                 * ways, and this is the ONLY ONE which works
                 * for some fucking reason.
                 *
                 * DO NOT TOUCH
                 */
                when (id) {
                    10 -> lowerlayeredpane.add(label, Integer(10+id))
                    9 -> upperlayeredpane.setLayer(label, 10+id)
                    else -> upperlayeredpane.add(label, Integer(10+id))
                }

                Inst.refresh()
                break
            }
        }
    }

    fun remove(label : JLabel){
        if (upperlayeredpane.components.contains(label))
            upperlayeredpane.remove(label)
        else if (lowerlayeredpane.components.contains(label))
            lowerlayeredpane.remove(label)
    }

    fun init(){
        jPanel.layout = BoxLayout(jPanel, BoxLayout.PAGE_AXIS)

        upperlayeredpane = JLayeredPane()

        val jLabel = JLabel(ImageIcon(File(Inst.loader.baseFolder.path + "/3x3.png").path))/*.apply { minimumSize = Dimension(160,160); alignmentX = 0.5f; alignmentY = 0.2f}*/
        jLabel.setBounds(0,0,240,240)
        upperlayeredpane.add(jLabel, JLayeredPane.DEFAULT_LAYER)

        val diff = 78
        var x = 0
        var y = 0
        for (i in 1..9){

            val label = JLabel()
            label.background = Color(139,139,139)
            listLabel.add(label)
            label.addMouseListener(SEditorActions())
            label.transferHandler = SEditorHandler()
            label.putClientProperty("number", i)
            label.isVisible = true
            label.isOpaque = true
            upperlayeredpane.add(label, Integer(i))

            if (x == diff*3) {
                x = 0
                y += diff
            }
            label.setBounds(x+18, y+18, 44,44)
            x += diff
        }
        upperlayeredpane.isVisible = true
        val secondpanel = JPanel(GridLayout(1,1)).apply { alignmentX = 0.5f }
        secondpanel.add(upperlayeredpane)
        //secondpanel.alignmentX = 0.5f
        secondpanel.minimumSize = Dimension(240,240)
        secondpanel.maximumSize = Dimension(240,240)
        secondpanel.preferredSize = Dimension(240,240)

        val mainPanel = JPanel()
        mainPanel.layout = BoxLayout(mainPanel, BoxLayout.PAGE_AXIS)
        mainPanel.add(Box.createVerticalStrut(40))
        mainPanel.add(secondpanel)
        mainPanel.add(Box.createVerticalStrut(10))
        val arrow = JLabel(ImageIcon(File(Inst.loader.baseFolder.path + "/arrow.png").path)).apply { alignmentX = 0.5f }
        mainPanel.add(arrow)

        mainPanel.add(Box.createVerticalStrut(10))

        lowerlayeredpane = JLayeredPane()
        lowerlayeredpane.setBounds(120,20,100,100)
        val resultLabel = JLabel()
        resultLabel.background = Color(139,139,139)
        listLabel.add(resultLabel)
        resultLabel.addMouseListener(SEditorActions())
        resultLabel.transferHandler = SEditorHandler()
        resultLabel.putClientProperty("number", 10)
        resultLabel.isVisible = true
        resultLabel.isOpaque = true
        resultLabel.setBounds(37,18,44,44)
        val oneslot = JLabel(ImageIcon(File(Inst.loader.baseFolder.path + "/oneslot.png").path)).apply { alignmentX = Component.CENTER_ALIGNMENT }
        oneslot.setBounds(18,0,78,78)
        lowerlayeredpane.add(oneslot, JLayeredPane.DEFAULT_LAYER)
        lowerlayeredpane.add(resultLabel, Integer(10))
        val thirdpanel = JPanel(GridLayout(1,1)).apply { alignmentX = Component.CENTER_ALIGNMENT }
        thirdpanel.add(lowerlayeredpane)
        thirdpanel.minimumSize = Dimension(120,120)
        thirdpanel.maximumSize = Dimension(120,120)
        thirdpanel.preferredSize = Dimension(120,120)

        mainPanel.add(thirdpanel)

        jPanel.add(mainPanel)

        val fourthpanel = JPanel(GridLayout(1,1)).apply { alignmentX = 0.5f }
        mirror.addActionListener(Inst.buttonActions)
        mirror.apply { preferredSize = Dimension(175,50); font = Inst.font; toolTipText = "Copies recipe into decomposer tab" }
        fourthpanel.minimumSize = Dimension(175,50)
        fourthpanel.maximumSize = Dimension(175,50)
        fourthpanel.add(mirror)

        jPanel.add(fourthpanel)
        jPanel.revalidate()
    }
}