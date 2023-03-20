package misc

import java.io.File
import javax.swing.JOptionPane

object Saver {

    fun save(){
        val listIndex = Inst.right.list.selectedIndex

        if (listIndex == -1)
            JOptionPane.showMessageDialog(null, "Please select an option");

        val name = Inst.right.demoList[listIndex]
        val file = File(Inst.loader.recipeFolder.toString() + "/" + name + ".chemrecipe")
        file.createNewFile()

        for (labelNum in 1..9){
            if (Inst.sEditor.getSynthesisLabels().containsKey(labelNum))
                file.writeText(labelNum.toString() + ":" + Inst.sEditor.getSynthesisLabels()[labelNum]?.toolTipText
                + ":" + Inst.sEditor.getSynthesisLabelAmt(labelNum) + "\n")
            else
                file.writeText("$labelNum:\n")
        }
    }

    fun reloadall(){

    }
}