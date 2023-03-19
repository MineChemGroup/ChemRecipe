package actions

import misc.Inst
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent


internal class RecipeRename : KeyAdapter() {
    override fun keyPressed(event: KeyEvent) {
        val ch: Char = event.keyChar
        if (ch.equals('\n', true)){

            val listIndex = Inst.right.list.selectedIndex
            if (listIndex != -1) {
                Inst.right.demoList[listIndex] = Inst.right.recipeName.text
                Inst.right.recipeName.text = ""
            }
        }
    }
}