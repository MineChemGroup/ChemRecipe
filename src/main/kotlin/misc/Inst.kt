package main.kotlin.misc

import main.kotlin.actions.ButtonActions
import main.kotlin.actions.ListActions
import main.kotlin.panels.CEditor
import main.kotlin.panels.Center
import main.kotlin.panels.Left
import main.kotlin.tooltip.CompoundLabel
import main.kotlin.tooltip.CustomLabel
import main.kotlin.tooltip.ElementLabel
import main.kotlin.tooltip.ItemLabel
import panels.Menu
import panels.Right
import panels.SEditor
import java.awt.Font
import java.nio.file.Path
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

    //fun paintOnee(path: Path, num : Int){
    //    Painter.paintOne(path, num)
    //}

    fun refresh(){
        SwingUtilities.updateComponentTreeUI(jframe)
        sEditor.jPanel.repaint()
        sEditor.jPanel.revalidate()
        cEditor.jPanel.repaint()
        cEditor.jPanel.revalidate()
        right.jPanel.repaint()
        right.jPanel.revalidate()
    }

    var currentDragged : CustomLabel? = null

    fun JLabel.copy() : JLabel {
        val label = JLabel()

        label.preferredSize = this.preferredSize
        label.icon = this.icon
        label.text = this.text
        label.bounds = this.bounds
        label.setUI(this.ui)
        label.toolTipText = this.toolTipText

        return label
    }

    fun CustomLabel.copyHandler() : CustomLabel{
        val label = when(this){
            is CompoundLabel -> CompoundLabel(this.info)
            is ElementLabel -> ElementLabel(this.info)
            is ItemLabel -> ItemLabel(this.info)
            else -> ItemLabel(this.info)
        }

        label.preferredSize = this.preferredSize
        label.icon = this.icon
        label.text = this.text
        label.bounds = this.bounds
        label.setUI(this.ui)
        label.toolTipText = this.toolTipText
        label.transferHandler = this.transferHandler
        for (listener in this.mouseListeners)
            label.addMouseListener(listener)

        return label
    }
}