package main.kotlin.actions

import main.kotlin.misc.Inst
import main.kotlin.tooltip.CustomLabel
import java.awt.Color
import java.awt.GraphicsEnvironment
import java.awt.Image
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.*


class SEditorActions : MouseAdapter() {

    override fun mouseEntered(e: MouseEvent?) {
        for (label in Inst.sEditor.defaultlabels) {
            if (label == e?.component) {
                label.background = Color(204,204,204)
                label.repaint()
                return
            }
        }
    }

    override fun mouseExited(e: MouseEvent?) {
        for (label in Inst.sEditor.defaultlabels) {
            if (label == e?.component) {
                label.background = Color(139,139,139)
                label.repaint()
                return
            }
        }
    }

    override fun mousePressed(e: MouseEvent?) {
        val c = e!!.source as CustomLabel
        if (c.icon == null)
            return
        val handler = c.transferHandler
        handler.dragImage = iconToImage(c.icon)
        handler.exportAsDrag(c, e, TransferHandler.COPY)
        Inst.sEditor.removeSpinner((c.getClientProperty("number") as Int))
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