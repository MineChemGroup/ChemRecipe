package panels

import misc.Inst
import java.awt.Component
import java.awt.Dimension
import javax.swing.*


class Left(val jPanel: JPanel = JPanel()) {

    val mcassetpanel = JPanel().apply { preferredSize = Dimension(160,5250); isVisible = true }
    val chemassetpanel = JPanel().apply { preferredSize = Dimension(160, 4150); isVisible = true }

    fun init(){
        for (file in Inst.loader.getAssets(Inst.loader.iconsFolder)){
            val label = JLabel(ImageIcon(file.path, file.nameWithoutExtension))
            mcassetpanel.add(label)
        }

        val scrollableArea0 = JScrollPane(mcassetpanel).apply { preferredSize = Dimension(320,500) }
        scrollableArea0.verticalScrollBarPolicy = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS
        scrollableArea0.horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER

        for ((i, file) in Inst.loader.getNumerical(Inst.loader.elementsFolder, 118).withIndex()){
            val label = JLabel(ImageIcon(file.path))
            chemassetpanel.add(label)
        }
        for ((i, file) in Inst.loader.getNumerical(Inst.loader.compoundsFolder, 121).withIndex()){
            val label = JLabel(ImageIcon(file.path))
            chemassetpanel.add(label)
        }

        val scrollableArea1 = JScrollPane(chemassetpanel).apply { preferredSize = Dimension(320,500) }
        scrollableArea1.verticalScrollBarPolicy = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS
        scrollableArea1.horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER

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