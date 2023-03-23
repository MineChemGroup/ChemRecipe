package main.kotlin.actions

import main.kotlin.misc.Inst
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import java.io.File
import javax.swing.JOptionPane


internal class RecipeRename : KeyAdapter() {
    override fun keyPressed(event: KeyEvent) {
        val ch: Char = event.keyChar
        if (ch.equals('\n', true)){

            for (piece in 0 until Inst.right.demoList.size()) {
                if (Inst.right.demoList[piece].equals(Inst.right.recipeName.text)) {
                    JOptionPane.showMessageDialog(null, "Another file already has this name")
                    return
                }
            }


            val listIndex = Inst.right.list.selectedIndex
            if (listIndex != -1) {
                val file = File(Inst.loader.recipeFolder.toString() + "/" + Inst.right.demoList[listIndex] + ".chemrecipe")
                if (file.exists())
                    file.renameTo(File(Inst.loader.recipeFolder.toString() + "/" + Inst.right.recipeName.text + ".chemrecipe"))
                Inst.right.demoList[listIndex] = Inst.right.recipeName.text
                Inst.right.recipeName.text = ""
            }
        }
    }
}