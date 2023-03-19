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

        if (Inst.sEditor.getResultLabel() != null)
            file.writeText("0:" + Inst.sEditor.getResultLabel()!!.toolTipText + ":" + Inst.sEditor.getResultLabelAmt() + "\n")
        else
            file.writeText("0:\n")

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