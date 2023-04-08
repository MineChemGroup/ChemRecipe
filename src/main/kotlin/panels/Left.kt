package main.kotlin.panels

//import eu.hoefel.chemistry.Element
import main.kotlin.actions.LeftMouseActions
import main.kotlin.actions.LeftPanelActions
import main.kotlin.misc.Element
import main.kotlin.tooltip.ElementLabel
import main.kotlin.misc.Inst
import main.kotlin.misc.Inst.copy
import main.kotlin.misc.Inst.copyHandler
import main.kotlin.search.ChemSearch
import main.kotlin.search.ItemSearch
import main.kotlin.tooltip.ItemLabel
import main.kotlin.tooltip.CompoundLabel
import main.kotlin.tooltip.CustomLabel
import main.kotlin.transfer.LeftTransferHandler
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Image
import java.io.File
import java.nio.file.Path
import java.util.*
import javax.swing.*
import kotlin.math.ceil
import kotlin.math.floor


class Left(val jPanel: JPanel = JPanel()) {

    val listmcassets = arrayListOf<CustomLabel>()
    val listchemassets = arrayListOf<CustomLabel>()

    val mcassetpanel = JPanel().apply { preferredSize = Dimension(160,4825)
        addComponentListener(LeftPanelActions()); isVisible = true }
    val chemassetpanel = JPanel().apply { preferredSize = Dimension(160, 1150)
        addComponentListener(LeftPanelActions()); isVisible = true }

    val itemSearchBar = JTextField()
    val chemSearchBar = JTextField()

    fun changeSizeDynamically(panel : JPanel){
        val components = panel.componentCount
        val columns = floor((panel.width-12).toDouble()/35.7)
        val rows = ceil(components/columns)
        panel.preferredSize = Dimension((Inst.jframe.width/3)-12, (rows.toInt()*40)+32)
    }

    fun refreshItems(){
        mcassetpanel.repaint()
        mcassetpanel.revalidate()
    }

    fun refreshChems(){
        chemassetpanel.repaint()
        chemassetpanel.revalidate()
    }

    fun loadCompound(num : Int){
        //println("loadCompound loading $num")

        var file = File(Inst.loader.compoundsFolder.path + "/$num.png")
        val label = CompoundLabel("Compound " + file.nameWithoutExtension)
        label.icon = ImageIcon(ImageIcon(file.path).image.getScaledInstance(32,32,Image.SCALE_SMOOTH))
        label.toolTipText = "Undefined Compound " + file.nameWithoutExtension
        label.preferredSize = Dimension(32,32)
        label.addMouseListener(LeftMouseActions())
        label.transferHandler = LeftTransferHandler("icon")
        if (ChemSearch.text == "") {
            chemassetpanel.add(label)
            listchemassets.add(label.copyHandler())
        } else{
            listchemassets.add(label.copyHandler())
        }
    }

    fun init(){

        for (file in Inst.loader.getAssets(Inst.loader.iconsFolder)){
            val label = ItemLabel(file.nameWithoutExtension)
            label.icon = ImageIcon(file.path)
            label.toolTipText = file.nameWithoutExtension.replace(Regex("[_]"), " ")
            label.addMouseListener(LeftMouseActions())
            label.transferHandler = LeftTransferHandler("icon")
            mcassetpanel.add(label)
            listmcassets.add(label.copyHandler())
        }

        val scrollableArea0 = JScrollPane(mcassetpanel).apply { preferredSize = Dimension(320,500) }
        scrollableArea0.verticalScrollBar.unitIncrement = 16
        scrollableArea0.verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
        scrollableArea0.horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER

        for ((i, file) in Inst.loader.getNumerical(Inst.loader.elementsFolder, 118).withIndex()){
            val label = ElementLabel("Element " + file.nameWithoutExtension)
            label.icon = ImageIcon(ImageIcon(file.path).image.getScaledInstance(32,32,Image.SCALE_SMOOTH))
            label.toolTipText = "Element " + file.nameWithoutExtension + "\n" + Element.getByAtomicNumber(file.nameWithoutExtension.toInt())!!.fullName
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            label.preferredSize = Dimension(32,32)
            label.addMouseListener(LeftMouseActions())
            label.transferHandler = LeftTransferHandler("icon")
            chemassetpanel.add(label)
            listchemassets.add(label.copyHandler())

        }
        //val compoundNum = Inst.loader.compoundsFolder.list()?.size
        val fileNums = arrayListOf<Int>()
        for (file in Inst.loader.compoundsFolder.listFiles()!!){
            fileNums.add(file.nameWithoutExtension.toInt())
        }
        fileNums.sort()
        for (num in fileNums){
            val file = File(Inst.loader.compoundsFolder.path + "/$num.png")
            val label = CompoundLabel("Compound " + file.nameWithoutExtension)
            label.icon = ImageIcon(ImageIcon(file.path).image.getScaledInstance(32,32,Image.SCALE_SMOOTH))
            label.toolTipText = "Undefined Compound " + file.nameWithoutExtension
            label.preferredSize = Dimension(32,32)
            label.addMouseListener(LeftMouseActions())
            label.transferHandler = LeftTransferHandler("icon")
            chemassetpanel.add(label)
            listchemassets.add(label.copyHandler())
        }

        val scrollableArea1 = JScrollPane(chemassetpanel).apply { preferredSize = Dimension(320,500) }
        scrollableArea1.verticalScrollBar.unitIncrement = 16
        scrollableArea1.verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
        scrollableArea1.horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER

        itemSearchBar.toolTipText = "Search in vanilla items"
        itemSearchBar.addKeyListener(ItemSearch())
        itemSearchBar.minimumSize = Dimension(180, 25)
        itemSearchBar.maximumSize = Dimension(1000, 25)

        chemSearchBar.toolTipText = "Search in chemistry items"
        chemSearchBar.addKeyListener(ChemSearch())
        chemSearchBar.minimumSize = Dimension(180, 25)
        chemSearchBar.maximumSize = Dimension(1000, 25)

        jPanel.layout = BoxLayout(jPanel, BoxLayout.PAGE_AXIS)

        val searchIconFile = File(Inst.loader.baseFolder.path + "/searchicon.png")
        val searchIconLabel = JLabel(ImageIcon(ImageIcon(searchIconFile.path).image.getScaledInstance(28,28,Image.SCALE_SMOOTH)))
        searchIconLabel.preferredSize = Dimension(28,28)


        val iconConstraints = GridBagConstraints()
        //c.gridwidth = GridBagConstraints.REMAINDER
        //iconConstraints.anchor = GridBagConstraints.FIRST_LINE_END
        iconConstraints.weightx = 0.0
        iconConstraints.weighty = 0.0
        val searchBarConstraints = GridBagConstraints()
        //searchBarConstraints.anchor = GridBagConstraints.CENTER
        searchBarConstraints.weightx = 1.0
        searchBarConstraints.weighty = 1.0
        searchBarConstraints.fill = GridBagConstraints.HORIZONTAL

        val upperSearchPanel = JPanel(GridBagLayout())
        upperSearchPanel.add(itemSearchBar, searchBarConstraints)
        upperSearchPanel.add(searchIconLabel.copy(), iconConstraints)
        jPanel.add(upperSearchPanel)

        jPanel.add(scrollableArea0)

        val lowerSearchPanel = JPanel(GridBagLayout())
        lowerSearchPanel.add(chemSearchBar, searchBarConstraints)
        lowerSearchPanel.add(searchIconLabel.copy(), iconConstraints)
        jPanel.add(lowerSearchPanel)

        jPanel.add(scrollableArea1)

        changeSizeDynamically(mcassetpanel)
        changeSizeDynamically(chemassetpanel)
    }

    fun getAsset(name : String) : CustomLabel?{
        for (asset in listchemassets){
            if (asset.info.equals(name)){
                return asset
            }
        }
        for (asset in listmcassets){
            if (asset.info.equals(name)){
                return asset
            }
        }
        return null
    }
}