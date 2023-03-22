package panels

import misc.Inst
import main.kotlin.actions.LeftMouseActions
import actions.LeftPanelActions
import transfer.LeftTransferHandler
import java.awt.*
import javax.swing.*


class Left(val jPanel: JPanel = JPanel()) {

    val listmcassets = arrayListOf<JLabel>()
    val listchemassets = arrayListOf<JLabel>()

    val mcassetpanel = JPanel().apply { preferredSize = Dimension(160,4825);
        addComponentListener(LeftPanelActions()); isVisible = true }
    val chemassetpanel = JPanel().apply { preferredSize = Dimension(160, 1150);
        addComponentListener(LeftPanelActions()); isVisible = true }

    fun init(){

        for (file in Inst.loader.getAssets(Inst.loader.iconsFolder)){
            val label = JLabel(ImageIcon(file.path))
            label.toolTipText = file.nameWithoutExtension
            label.addMouseListener(LeftMouseActions())
            label.transferHandler = LeftTransferHandler("icon")
            mcassetpanel.add(label)
            listmcassets.add(label)
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
            listchemassets.add(label)
        }
        for ((i, file) in Inst.loader.getNumerical(Inst.loader.compoundsFolder, 121).withIndex()){
            val label = JLabel(ImageIcon(ImageIcon(file.path).image.getScaledInstance(32,32,Image.SCALE_SMOOTH)))
            label.toolTipText = "Compound " + file.nameWithoutExtension
            label.preferredSize = Dimension(32,32)
            label.addMouseListener(LeftMouseActions())
            label.transferHandler = LeftTransferHandler("icon")
            chemassetpanel.add(label)
            listchemassets.add(label)
        }

        val scrollableArea1 = JScrollPane(chemassetpanel).apply { preferredSize = Dimension(320,500) }
        scrollableArea1.verticalScrollBar.unitIncrement = 16
        scrollableArea1.verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
        scrollableArea1.horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER

        jPanel.layout = GridLayout(2,1)
        jPanel.add(scrollableArea0)
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