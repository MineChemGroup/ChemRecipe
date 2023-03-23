package main.kotlin.panels

import main.kotlin.actions.LeftMouseActions
import main.kotlin.actions.LeftPanelActions
import main.kotlin.misc.Inst
import main.kotlin.misc.Inst.copy
import main.kotlin.misc.Inst.copyHandler
import main.kotlin.search.ChemSearch
import main.kotlin.search.ItemSearch
import transfer.LeftTransferHandler
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.GridLayout
import java.awt.Image
import java.io.File
import javax.swing.*


class Left(val jPanel: JPanel = JPanel()) {

    val listmcassets = arrayListOf<JLabel>()
    val listchemassets = arrayListOf<JLabel>()

    val mcassetpanel = JPanel().apply { preferredSize = Dimension(160,4825);
        addComponentListener(LeftPanelActions()); isVisible = true }
    val chemassetpanel = JPanel().apply { preferredSize = Dimension(160, 1150);
        addComponentListener(LeftPanelActions()); isVisible = true }

    val itemSearchBar = JTextField()
    val chemSearchBar = JTextField()

    fun refreshItems(){
        mcassetpanel.repaint()
        mcassetpanel.revalidate()
    }

    fun refreshChems(){
        chemassetpanel.repaint()
        chemassetpanel.revalidate()
    }

    fun init(){

        for (file in Inst.loader.getAssets(Inst.loader.iconsFolder)){
            val label = JLabel(ImageIcon(file.path))
            label.toolTipText = file.nameWithoutExtension
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
            val label = JLabel(ImageIcon(ImageIcon(file.path).image.getScaledInstance(32,32,Image.SCALE_SMOOTH)))
            label.toolTipText = "Element " + file.nameWithoutExtension
            label.preferredSize = Dimension(32,32)
            label.addMouseListener(LeftMouseActions())
            label.transferHandler = LeftTransferHandler("icon")
            chemassetpanel.add(label)
            listchemassets.add(label.copyHandler())
        }
        for ((i, file) in Inst.loader.getNumerical(Inst.loader.compoundsFolder, 121).withIndex()){
            val label = JLabel(ImageIcon(ImageIcon(file.path).image.getScaledInstance(32,32,Image.SCALE_SMOOTH)))
            label.toolTipText = "Compound " + file.nameWithoutExtension
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
    }

    fun getAsset(name : String) : JLabel?{
        for (asset in listchemassets){
            if (asset.toolTipText.equals(name)){
                return asset
            }
        }
        for (asset in listmcassets){
            if (asset.toolTipText.equals(name)){
                return asset
            }
        }
        return null
    }
}