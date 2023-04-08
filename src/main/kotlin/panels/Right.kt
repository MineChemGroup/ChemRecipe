package panels

import main.kotlin.actions.ListActions
import main.kotlin.actions.RecipeRename
import main.kotlin.list.CustomCellRenderer
import main.kotlin.list.CustomUI
import main.kotlin.misc.Inst
import main.kotlin.misc.Inst.copy
import main.kotlin.search.ListSearch
import java.awt.*
import java.io.File
import javax.swing.*


class Right(val jPanel: JPanel = JPanel()){

    val openfolder = JButton("Open Folder").apply { alignmentX = 0.5f}
    val createnew = JButton("Create New").apply { alignmentX = 0.5f}
    val save = JButton("Save Recipe").apply { alignmentX = 0.5f}
    val remove = JButton("Remove Recipe").apply { alignmentX = 0.5f }

    val recipeName = JTextField()

    //lateinit var list : CustomList<String>
    lateinit var list : JList<String>
    val demoList: DefaultListModel<String> = DefaultListModel<String>()

    val listSearchBar = JTextField()

    var text = ""

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
        //list = CustomList<String>()
        list = JList<String>()
        list.model = demoList
        list.toolTipText = "List of all loaded recipes"
        list.font = Inst.font
        list.selectionMode = ListSelectionModel.SINGLE_SELECTION
        list.addListSelectionListener(ListActions())
        list.ui = CustomUI()
        list.cellRenderer = CustomCellRenderer()

        val scrollableArea0 = JScrollPane(list).apply { preferredSize = Dimension(340,800) }
        scrollableArea0.verticalScrollBar.unitIncrement = 16
        scrollableArea0.verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        scrollableArea0.horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER

        recipeName.toolTipText = "Rename recipe, press Enter to confirm"
        recipeName.minimumSize = Dimension(180, 25)
        recipeName.maximumSize = Dimension(1000,25)
        recipeName.addKeyListener(RecipeRename())
        jPanel.add(recipeName)

        listSearchBar.toolTipText = "Search in recipes"
        listSearchBar.addKeyListener(ListSearch())
        listSearchBar.minimumSize = Dimension(180, 25)
        listSearchBar.maximumSize = Dimension(1000, 25)
        val searchIconFile = File(Inst.loader.baseFolder.path + "/searchicon.png")
        val searchIconLabel = JLabel(ImageIcon(ImageIcon(searchIconFile.path).image.getScaledInstance(28,28, Image.SCALE_SMOOTH)))
        searchIconLabel.preferredSize = Dimension(28,28)

        val iconConstraints = GridBagConstraints()
        iconConstraints.weightx = 0.0
        iconConstraints.weighty = 0.0

        val searchBarConstraints = GridBagConstraints()
        searchBarConstraints.weightx = 1.0
        searchBarConstraints.weighty = 1.0
        searchBarConstraints.fill = GridBagConstraints.HORIZONTAL

        val listSearchPanel = JPanel(GridBagLayout())
        listSearchPanel.add(listSearchBar, searchBarConstraints)
        listSearchPanel.add(searchIconLabel.copy(), iconConstraints)
        jPanel.add(listSearchPanel)

        jPanel.add(scrollableArea0)

        jPanel.revalidate()
    }
}