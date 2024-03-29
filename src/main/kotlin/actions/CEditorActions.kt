package main.kotlin.actions

import main.kotlin.misc.Inst
import main.kotlin.tooltip.CustomLabel
import java.awt.Color
import java.awt.GraphicsEnvironment
import java.awt.Image
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.Icon
import javax.swing.ImageIcon
import javax.swing.TransferHandler

class CEditorActions : MouseAdapter() {

    override fun mouseEntered(e: MouseEvent?) {
        if (Inst.cEditor.resultLabel == e?.component) {
            Inst.cEditor.resultLabel.background = Color(204,204,204)
            Inst.cEditor.resultLabel.repaint()
            return
        }
    }

    override fun mouseExited(e: MouseEvent?) {
        if (Inst.cEditor.resultLabel == e?.component) {
            Inst.cEditor.resultLabel.background = Color(139, 139, 139)
            Inst.cEditor.resultLabel.repaint()
            return
        }
    }

    override fun mousePressed(e: MouseEvent?) {
        if (e?.source !is CustomLabel)
            return

        val c = e.source as CustomLabel
        if (c.icon == null)
            return
        val handler = c.transferHandler
        handler.dragImage = iconToImage(c.icon)
        handler.exportAsDrag(c, e, TransferHandler.COPY)
        if (Inst.cEditor.layeredpane1.getComponentCountInLayer(21) > 0){
            Inst.cEditor.layeredpane1.remove(Inst.cEditor.layeredpane1.getComponentsInLayer(21)[0])
        }
    }

    fun iconToImage(icon: Icon): Image? {
        return if (icon is ImageIcon) {
            icon.image
        } else {
            val w = icon.iconWidth
            val h = icon.iconHeight
            val ge = GraphicsEnvironment.getLocalGraphicsEnvironment()
            val gd = ge.defaultScreenDevice
            val gc = gd.defaultConfiguration
            val image = gc.createCompatibleImage(w, h)
            val g = image.createGraphics()
            icon.paintIcon(null, g, 0, 0)
            g.dispose()
            image
        }
    }

}