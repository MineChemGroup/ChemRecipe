package misc

import java.io.File
import javax.swing.JOptionPane

object Saver {

    fun save(){
        if (Inst.right.list.selectedIndex == -1)
            JOptionPane.showMessageDialog(null, "Please select an option");

        val name = Inst.right.demoList[Inst.right.list.selectedIndex]
        val file = File(Inst.loader.recipeFolder.path + name)

        if (Inst.sEditor.getResultLabel() != null){
            
        }
    }

    fun reloadall(){

    }
}