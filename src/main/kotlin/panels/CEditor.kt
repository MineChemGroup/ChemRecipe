package panels

import misc.Inst
import actions.SEditorActions
import transfer.CEditorHandler
import java.awt.Color
import java.awt.Component
import java.awt.Dimension
import java.awt.GridLayout
import java.io.File
import javax.swing.*

class CEditor(val jPanel: JPanel = JPanel()) {

    val listLabel = arrayListOf<JLabel>()

    fun init(){
        jPanel.layout = BoxLayout(jPanel, BoxLayout.PAGE_AXIS)

        jPanel.add(Box.createVerticalStrut(40))

        val layeredpane1 = JLayeredPane()
        layeredpane1.setBounds(120,20,100,100)
        val resultLabel = JLabel()
        resultLabel.background = Color(139,139,139)
        listLabel.add(resultLabel)
        resultLabel.addMouseListener(SEditorActions())
        resultLabel.transferHandler = CEditorHandler()
        resultLabel.isVisible = true
        resultLabel.isOpaque = true
        resultLabel.putClientProperty("number", 11)
        resultLabel.setBounds(32,18,44,44)
        val oneslot = JLabel(ImageIcon(File(Inst.loader.baseFolder.path + "/oneslot.png").path)).apply { alignmentX = Component.CENTER_ALIGNMENT }
        oneslot.setBounds(12,0,78,78)
        layeredpane1.add(oneslot, JLayeredPane.DEFAULT_LAYER)
        layeredpane1.add(resultLabel, Integer(10))
        val firstpanel = JPanel(GridLayout(1,1)).apply { alignmentX = Component.CENTER_ALIGNMENT }
        firstpanel.add(layeredpane1)
        firstpanel.minimumSize = Dimension(100,100)
        firstpanel.maximumSize = Dimension(100,100)
        firstpanel.preferredSize = Dimension(100,100)

        jPanel.add(firstpanel)

        val arrowPanel = JPanel()
        arrowPanel.minimumSize = Dimension(100,100)
        arrowPanel.maximumSize = Dimension(100,100)
        arrowPanel.preferredSize = Dimension(100,100)
        arrowPanel.layout = BoxLayout(arrowPanel, BoxLayout.PAGE_AXIS)
        val arrow = JLabel(ImageIcon(File(Inst.loader.baseFolder.path + "/arrow.png").path)).apply { alignmentX = 0.5f }
        arrowPanel.add(arrow)

        jPanel.add(arrowPanel)

        val listpanel = JPanel()
        listpanel.minimumSize = Dimension(220,300)
        listpanel.maximumSize = Dimension(220,300)
        listpanel.preferredSize = Dimension(220,300)
        val demoList: DefaultListModel<JLabel> = DefaultListModel<JLabel>()
        val list = JList(demoList)
        val scrollableArea0 = JScrollPane(list).apply { preferredSize = Dimension(340,800) }
        scrollableArea0.verticalScrollBar.unitIncrement = 16
        scrollableArea0.verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        scrollableArea0.horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        jPanel.add(scrollableArea0)
    }
}