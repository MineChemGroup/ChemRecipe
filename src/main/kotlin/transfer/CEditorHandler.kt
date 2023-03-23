package transfer

import main.kotlin.actions.CEditorActions
import main.kotlin.actions.LeftMouseActions
import main.kotlin.misc.Inst
import main.kotlin.tooltip.CustomLabel
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.Transferable
import java.awt.event.InputEvent
import javax.swing.JComponent

import javax.swing.TransferHandler

class CEditorHandler() : TransferHandler("CEditor") {

    override fun exportAsDrag(comp: JComponent?, e: InputEvent?, action: Int) {
        Inst.currentDragged = (comp as CustomLabel)
        super.exportAsDrag(comp, e, action)
    }

    override fun canImport(comp: JComponent?, transferFlavors: Array<out DataFlavor>?): Boolean {
        if (comp is CustomLabel){
            if ((comp as CustomLabel).getClientProperty("connected") != null){
                return false
            }
        }
        return true
    }

    override fun importData(comp: JComponent?, t: Transferable?): Boolean {
        Inst.cEditor.add(Inst.currentDragged!!)
        Inst.currentDragged?.removeMouseListener(LeftMouseActions())
        Inst.currentDragged?.addMouseListener(CEditorActions())
        return true
    }
}