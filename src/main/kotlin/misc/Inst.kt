package main.kotlin.misc

import main.kotlin.actions.ButtonActions
import main.kotlin.actions.ListActions
import main.kotlin.misc.Inst.copy
import main.kotlin.misc.Inst.copyHandler
import main.kotlin.panels.CEditor
import main.kotlin.panels.Center
import main.kotlin.panels.Left
import main.kotlin.tooltip.CompoundLabel
import main.kotlin.tooltip.ElementLabel
import main.kotlin.tooltip.ItemLabel
import org.bukkit.entity.Item
import panels.*
import java.awt.Font
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.SwingUtilities

object Inst {
    val jframe = JFrame("ChemRecipe")

    lateinit var font: Font

    val loader = Loader()
    val sEditor = SEditor()
    val cEditor = CEditor()
    val center = Center()
    val left = Left()
    val right = Right()
    val buttonActions = ButtonActions()
    val menu = Menu()
    val listActions = ListActions()

    fun refresh(){
        SwingUtilities.updateComponentTreeUI(jframe)
        sEditor.jPanel.repaint()
        sEditor.jPanel.revalidate()
        cEditor.jPanel.repaint()
        cEditor.jPanel.revalidate()
        right.jPanel.repaint()
        right.jPanel.revalidate()
    }

    var currentDragged : JLabel? = null

    fun JLabel.copy() : JLabel{
        val label = when(this){
            is CompoundLabel -> CompoundLabel()
            is ElementLabel -> ElementLabel()
            is ItemLabel -> ItemLabel()
            else -> ItemLabel()
        }

        label.preferredSize = this.preferredSize
        label.icon = this.icon
        label.text = this.text
        label.bounds = this.bounds
        label.setUI(this.ui)
        label.toolTipText = this.toolTipText

        return label
    }

    fun JLabel.copyHandler() : JLabel{
        val label = when(this){
            is CompoundLabel -> CompoundLabel()
            is ElementLabel -> ElementLabel()
            is ItemLabel -> ItemLabel()
            else -> ItemLabel()
        }

        label.preferredSize = this.preferredSize
        label.icon = this.icon
        label.text = this.text
        label.bounds = this.bounds
        label.setUI(this.ui)
        label.toolTipText = this.toolTipText
        label.transferHandler = this.transferHandler
        label.addMouseListener(this.mouseListeners[0])

        return label
    }
}