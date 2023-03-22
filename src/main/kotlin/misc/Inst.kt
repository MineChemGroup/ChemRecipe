package misc

import actions.ButtonActions
import main.kotlin.actions.ListActions
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
        val label = JLabel()
        label.icon = this.icon
        label.text = this.text
        label.bounds = this.bounds
        label.setUI(this.ui)
        label.toolTipText = this.toolTipText

        return label
    }
}