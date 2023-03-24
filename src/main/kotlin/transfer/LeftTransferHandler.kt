package main.kotlin.transfer

import main.kotlin.actions.SEditorActions
import main.kotlin.misc.Inst
import main.kotlin.misc.Inst.copy
import main.kotlin.misc.Inst.copyHandler
import main.kotlin.tooltip.CustomLabel
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.Transferable
import java.awt.event.InputEvent
import javax.swing.JComponent

import javax.swing.TransferHandler

class LeftTransferHandler(val name : String) : TransferHandler(name) {

    override fun exportAsDrag(comp: JComponent?, e: InputEvent?, action: Int) {
        Inst.currentDragged = (comp as CustomLabel).copyHandler()
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
        return true
    }

    /*
    override fun importData(support: TransferSupport?): Boolean {
        return canImport()
    }
    */

    override fun importData(comp: JComponent?, t: Transferable?): Boolean {
        for (s in Inst.sEditor.upperlayeredpane.components){
            if (s == Inst.currentDragged){
                Inst.sEditor.upperlayeredpane.remove(s)
                break
            }
        }
        if (Inst.sEditor.lowerlayeredpane.getComponentCountInLayer(20) > 0){
            if (Inst.sEditor.lowerlayeredpane.getComponentsInLayer(20)[0] == Inst.currentDragged){
                Inst.sEditor.lowerlayeredpane.remove(Inst.currentDragged)
            }
        }
        if (Inst.cEditor.layeredpane1.getComponentCountInLayer(20) > 0){
            if (Inst.cEditor.layeredpane1.getComponentsInLayer(20)[0] == Inst.currentDragged){
                Inst.cEditor.layeredpane1.remove(Inst.currentDragged)
            }
        }
        Inst.refresh()
        return true
    }

}