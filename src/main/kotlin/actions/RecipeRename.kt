package actions

import misc.Inst
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import java.io.File


internal class RecipeRename : KeyAdapter() {
    override fun keyPressed(event: KeyEvent) {
        val ch: Char = event.keyChar
        if (ch.equals('\n', true)){

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