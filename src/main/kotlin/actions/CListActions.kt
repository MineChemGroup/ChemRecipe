package actions

import misc.Inst
import java.awt.Dimension
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent

class CListActions : ComponentAdapter() {

    override fun componentResized(e: ComponentEvent?) {

        //if (e?.component == Inst.cEditor.jPanel){
            val width = Inst.cEditor.jPanel.width

            println("width $width")
            println("height ${Inst.jframe.height}")

            Inst.cEditor.scrollableArea0.preferredSize = Dimension(width, 44)
        //}
        Inst.refresh()
    }

}