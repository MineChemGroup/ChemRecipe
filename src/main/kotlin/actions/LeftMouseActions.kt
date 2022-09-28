package actions

import java.awt.GraphicsEnvironment
import java.awt.Image
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.*


class LeftMouseActions : MouseAdapter() {

    override fun mouseEntered(e: MouseEvent?) {

    }

    override fun mouseExited(e: MouseEvent?) {

    }

    override fun mousePressed(e: MouseEvent?) {
        val c = e!!.source as JComponent
        //c.addMouseListener(this)
        val handler = c.transferHandler
        handler.dragImage = iconToImage((c as JLabel).icon)
        handler.exportAsDrag(c, e, TransferHandler.COPY)

        //println("mousePressed")
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