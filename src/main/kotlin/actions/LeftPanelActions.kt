package main.kotlin.actions

import main.kotlin.misc.Inst
import java.awt.Dimension
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.round

class LeftPanelActions : ComponentAdapter() {

    override fun componentResized(e : ComponentEvent){

        if (e.component == Inst.left.chemassetpanel)
            Inst.left.changeSizeDynamically(Inst.left.chemassetpanel)
        else if (e.component == Inst.left.mcassetpanel)
            Inst.left.changeSizeDynamically(Inst.left.mcassetpanel)

    }

}