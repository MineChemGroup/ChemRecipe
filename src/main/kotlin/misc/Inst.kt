package misc

import panels.*
import javax.swing.JFrame
import javax.swing.SwingUtilities

object Inst {
    val jframe = JFrame("ChemRecipe")
    val loader = Loader()
    val mEditor = MEditor().apply { init() }
    val left = Left()
    val right = Right()
    val actions = Actions()
    val menu = Menu().apply { init() }

    fun refresh(){
        SwingUtilities.updateComponentTreeUI(jframe)
    }
}