package misc

import java.io.File
import javax.swing.JOptionPane

object Saver {

    fun save(){
        val listIndex = Inst.right.list.selectedIndex

        if (listIndex == -1)
            JOptionPane.showMessageDialog(null, "Please select an option");

        val name = Inst.right.demoList[listIndex]
        val file = File(Inst.loader.recipeFolder.path + name)

        if (Inst.sEditor.getResultLabel() != null){

        }
    }

    fun reloadall(){

    }
}