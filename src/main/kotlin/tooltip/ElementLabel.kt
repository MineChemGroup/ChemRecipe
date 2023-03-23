package main.kotlin.tooltip

import main.kotlin.misc.Inst
import java.awt.Color
import javax.swing.*


internal class ElementLabel : JLabel() {
    override fun createToolTip(): JToolTip? {
        val tip = super.createToolTip()
        tip.foreground = Color.CYAN
        tip.font = Inst.font
        return tip
    }
}