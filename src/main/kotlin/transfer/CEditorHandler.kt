package transfer

import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.Transferable
import javax.swing.JComponent
import javax.swing.TransferHandler

class CEditorHandler() : TransferHandler("CEditor") {

    override fun canImport(comp: JComponent?, transferFlavors: Array<out DataFlavor>?): Boolean {
        return true
    }

    /*override fun importData(comp: JComponent?, t: Transferable?): Boolean {

    }*/
}