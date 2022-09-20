package panels

import misc.Inst
import java.awt.Dimension
import javax.swing.*


class Right(val jPanel: JPanel = JPanel()){

    val openfolder = JButton("Open Folder").apply { alignmentX = 0.5f}


    fun init(){
        openfolder.addActionListener(Inst.actions)
        jPanel.layout = BoxLayout(jPanel, BoxLayout.PAGE_AXIS)
        jPanel.add(openfolder)
        openfolder.apply { preferredSize = Dimension(175,50); font = Inst.font; toolTipText = "Open Folder containing recipes" }
        openfolder.minimumSize = Dimension(175,50)
        openfolder.maximumSize = Dimension(175,50)

        val demoList: DefaultListModel<String> = DefaultListModel<String>()
        for (i in 1..20){
            demoList.addElement(i.toString())
        }
        val list = JList(demoList).apply { toolTipText = "List of all loaded recipes"}
        list.font = Inst.font

        val scrollableArea0 = JScrollPane(list).apply { preferredSize = Dimension(340,800) }
        scrollableArea0.verticalScrollBar.unitIncrement = 16
        scrollableArea0.verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        scrollableArea0.horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER

        jPanel.add(scrollableArea0)

        jPanel.revalidate()
    }
}