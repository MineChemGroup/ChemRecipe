package panels

import main.kotlin.actions.ListActions
import actions.RecipeRename
import misc.Inst
import java.awt.Dimension
import java.awt.GridLayout
import javax.swing.*


class Right(val jPanel: JPanel = JPanel()){

    val openfolder = JButton("Open Folder").apply { alignmentX = 0.5f}
    val createnew = JButton("Create New").apply { alignmentX = 0.5f}
    val save = JButton("Save Recipe").apply { alignmentX = 0.5f}
    val remove = JButton("Remove Recipe").apply { alignmentX = 0.5f }

    lateinit var list : JList<String>
    val demoList: DefaultListModel<String> = DefaultListModel<String>()

    val recipeName = JTextField()
    fun init(){
        jPanel.layout = BoxLayout(jPanel, BoxLayout.PAGE_AXIS)

        createnew.addActionListener(Inst.buttonActions)
        createnew.apply { preferredSize = Dimension(175,50); font = Inst.font }
        createnew.minimumSize = Dimension(175,50)
        createnew.maximumSize = Dimension(175,50)

        save.addActionListener(Inst.buttonActions)
        save.apply { preferredSize = Dimension(175,50); font = Inst.font }
        save.minimumSize = Dimension(175,50)
        save.maximumSize = Dimension(175,50)

        remove.addActionListener(Inst.buttonActions)
        remove.apply { preferredSize = Dimension(175,50); font = Inst.font}
        remove.minimumSize = Dimension(175,50)
        remove.maximumSize = Dimension(175,50)

        openfolder.addActionListener(Inst.buttonActions)
        openfolder.apply { preferredSize = Dimension(175,50); font = Inst.font; toolTipText = "Open Folder containing recipes" }
        openfolder.minimumSize = Dimension(175,50)
        openfolder.maximumSize = Dimension(175,50)

        val upperpanel = JPanel(GridLayout(2,2,3,3))
        upperpanel.minimumSize = Dimension(400,100)
        upperpanel.maximumSize = Dimension(400,100)
        upperpanel.preferredSize = Dimension(400,100)

        upperpanel.add(openfolder)
        upperpanel.add(createnew)
        upperpanel.add(save)
        upperpanel.add(remove)

        jPanel.add(upperpanel)

        /*for (i in 1..20){
            demoList.addElement(i.toString())
        }*/
        list = JList(demoList).apply { toolTipText = "List of all loaded recipes"}
        list.font = Inst.font
        list.selectionMode = ListSelectionModel.SINGLE_SELECTION
        list.addListSelectionListener(ListActions())

        val scrollableArea0 = JScrollPane(list).apply { preferredSize = Dimension(340,800) }
        scrollableArea0.verticalScrollBar.unitIncrement = 16
        scrollableArea0.verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        scrollableArea0.horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER

        recipeName.toolTipText = "Rename recipe, press Enter to confirm"
        recipeName.minimumSize = Dimension(90, 23)
        recipeName.addKeyListener(RecipeRename())
        jPanel.add(recipeName)

        jPanel.add(scrollableArea0)

        jPanel.revalidate()
    }
}