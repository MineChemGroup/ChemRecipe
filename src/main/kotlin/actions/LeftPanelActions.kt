package actions

import misc.Inst
import java.awt.Dimension
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent

class LeftPanelActions : ComponentAdapter() {

    override fun componentResized(e : ComponentEvent){
        val width = e.component.width

        if (e.component == Inst.left.mcassetpanel){
            val height = (1026*32*32)/(width*0.68)
            Inst.left.mcassetpanel.preferredSize = Dimension(width, height.toInt())
            //Inst.left.mcassetpanel.size = Dimension(width, height.toInt())
        } else if (e.component == Inst.left.chemassetpanel){
            val height = (240*32*32)/(width*0.68)
            Inst.left.chemassetpanel.preferredSize = Dimension(width, height.toInt())
            //Inst.left.chemassetpanel.size = Dimension(width, height)
        }

        //Inst.left.jPanel.revalidate()
        Inst.refresh()
    }
}