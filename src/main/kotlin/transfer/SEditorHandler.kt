package transfer

import misc.Inst
import misc.Inst.copy
import org.apache.commons.lang3.SerializationUtils
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.Transferable
import java.awt.event.InputEvent
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.TransferHandler

class SEditorHandler() : TransferHandler("icon") {

    override fun exportAsDrag(comp: JComponent?, e: InputEvent?, action: Int) {
        Inst.currentDragged = (comp as JLabel)
        super.exportAsDrag(comp, e, action)
    }

    override fun canImport(comp: JComponent?, transferFlavors: Array<out DataFlavor>?): Boolean {
        return true
    }

    override fun importData(comp: JComponent?, t: Transferable?): Boolean {
        Inst.sEditor.add(Inst.currentDragged!!)
        return true
    }

}