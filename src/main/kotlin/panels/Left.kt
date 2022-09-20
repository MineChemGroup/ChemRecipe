package panels

import misc.Inst
import java.awt.*
import javax.swing.*


class Left(val jPanel: JPanel = JPanel()) {

    val mcassetpanel = JPanel().apply { preferredSize = Dimension(160,4825); isVisible = true }
    val chemassetpanel = JPanel().apply { preferredSize = Dimension(160, 1150); isVisible = true }

    fun init(){
        for (file in Inst.loader.getAssets(Inst.loader.iconsFolder)){
            val label = JLabel(ImageIcon(file.path))
            label.toolTipText = file.nameWithoutExtension
            mcassetpanel.add(label)
        }

        val scrollableArea0 = JScrollPane(mcassetpanel).apply { preferredSize = Dimension(320,500) }
        scrollableArea0.verticalScrollBar.unitIncrement = 16
        scrollableArea0.verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
        scrollableArea0.horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER

        for ((i, file) in Inst.loader.getNumerical(Inst.loader.elementsFolder, 118).withIndex()){
            //val label = JLabel(ImageIcon(file.path))
            val label = JLabel(ImageIcon(ImageIcon(file.path).image.getScaledInstance(32,32,Image.SCALE_SMOOTH)))
            label.toolTipText = "Element " + file.nameWithoutExtension
            label.preferredSize = Dimension(32,32)
            chemassetpanel.add(label)
        }
        for ((i, file) in Inst.loader.getNumerical(Inst.loader.compoundsFolder, 121).withIndex()){
            //val label = JLabel(ImageIcon(file.path))
            val label = JLabel(ImageIcon(ImageIcon(file.path).image.getScaledInstance(32,32,Image.SCALE_SMOOTH)))
            label.toolTipText = "Compound " + file.nameWithoutExtension
            label.preferredSize = Dimension(32,32)
            chemassetpanel.add(label)
        }

        val scrollableArea1 = JScrollPane(chemassetpanel).apply { preferredSize = Dimension(320,500) }
        scrollableArea1.verticalScrollBar.unitIncrement = 16
        scrollableArea1.verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
        scrollableArea1.horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER

        jPanel.layout = GridLayout(2,1)
        jPanel.add(scrollableArea0)
        jPanel.add(scrollableArea1)
    }
    /*
    fun redo(){
        val components: Array<JLabel> = chemassetpanel.components as Array<JLabel>
        chemassetpanel.removeAll()
        var i = 1
        while (chemassetpanel.components.size < 239){
            val it = components.iterator()
            while (it.hasNext()){
                val label = it.next()
                if (label.text.equals())
            }
        }
    }

     */
}