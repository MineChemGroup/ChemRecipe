package transfer

import actions.SEditorActions
import misc.Inst
import misc.Inst.copy
import org.apache.commons.lang3.SerializationUtils
import java.awt.MouseInfo
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.Transferable
import java.awt.event.InputEvent
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.TransferHandler

class LeftTransferHandler(val name : String) : TransferHandler(name) {

    override fun exportAsDrag(comp: JComponent?, e: InputEvent?, action: Int) {
        Inst.currentDragged = (comp as JLabel).copy()
        if (Inst.center.tabbed.selectedComponent == Inst.sEditor.jPanel) {
            Inst.currentDragged!!.addMouseListener(SEditorActions())
            Inst.currentDragged!!.transferHandler = SEditorHandler()
        }  else if (Inst.center.tabbed.selectedComponent == Inst.cEditor.jPanel) {
            //Inst.currentDragged!!.addMouseListener()
        }
        super.exportAsDrag(comp, e, action)
    }

    /*
    override fun canImport(support: TransferSupport?): Boolean {
        return canImport()
    }
    */

    override fun canImport(comp: JComponent?, transferFlavors: Array<out DataFlavor>?): Boolean {
        return canImport()
    }

    /*
    override fun importData(support: TransferSupport?): Boolean {
        return canImport()
    }
    */

    override fun importData(comp: JComponent?, t: Transferable?): Boolean {
        return canImport()
    }

    private fun canImport() : Boolean {
        return !Inst.left.jPanel.bounds.contains(MouseInfo.getPointerInfo().location)
    }

}