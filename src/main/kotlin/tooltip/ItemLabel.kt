package main.kotlin.tooltip

import main.kotlin.misc.Inst
import java.awt.Color

import javax.swing.JToolTip

internal class ItemLabel(info : String) : CustomLabel(info) {
    override fun createToolTip(): JToolTip? {
        val tip = super.createToolTip()
        tip.foreground = Color.YELLOW
        tip.font = Inst.font
        return tip
    }
}