package main.kotlin.tooltip

import main.kotlin.misc.Inst
import java.awt.Color
import javax.swing.JLabel
import javax.swing.JToolTip

internal class CompoundLabel : JLabel() {
    override fun createToolTip(): JToolTip? {
        val tip = super.createToolTip()
        tip.foreground = Color.ORANGE
        tip.font = Inst.font
        return tip
    }
}