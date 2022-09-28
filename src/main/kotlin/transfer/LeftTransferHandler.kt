package transfer

import misc.Inst
import java.awt.MouseInfo
import java.awt.datatransfer.Transferable
import java.awt.event.InputEvent
import javax.swing.JComponent
import javax.swing.TransferHandler

class LeftTransferHandler() : TransferHandler("left") {

    override fun exportAsDrag(comp: JComponent?, e: InputEvent?, action: Int) {
        super.exportAsDrag(comp, e, action)
    }


    override fun canImport(support: TransferSupport?): Boolean {
        return !Inst.left.jPanel.bounds.contains(MouseInfo.getPointerInfo().location)
    }


    override fun importData(comp: JComponent?, t: Transferable?): Boolean {
        return !Inst.left.jPanel.bounds.contains(MouseInfo.getPointerInfo().location)
    }

}