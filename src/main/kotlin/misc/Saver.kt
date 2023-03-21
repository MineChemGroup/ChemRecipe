package misc

import java.io.File
import java.io.PrintWriter
import javax.swing.JOptionPane


object Saver {

    fun save(){
        val listIndex = Inst.right.list.selectedIndex

        if (listIndex == -1)
            JOptionPane.showMessageDialog(null, "Please select an option")

        val name = Inst.right.demoList[listIndex]
        val file = File(Inst.loader.recipeFolder.toString() + "/" + name + ".chemrecipe")

        if (file.exists()) {
            val pw = PrintWriter(file)
            pw.close()
        } else {
            file.createNewFile()
        }

        file.appendText("sEditor\n")
        for (labelNum in 1..9){
            if (Inst.sEditor.getSynthesisLabels().containsKey(labelNum))
                file.appendText(labelNum.toString() + ":" + Inst.sEditor.getSynthesisLabels()[labelNum]?.toolTipText
                        + ":" + Inst.sEditor.getSynthesisLabelAmt(labelNum) + "\n")
            else
                file.appendText("$labelNum:\n")
        }
        if (Inst.sEditor.getResultLabel() != null)
            file.appendText("S:" + Inst.sEditor.getResultLabel()?.toolTipText + ":" + Inst.sEditor.getResultLabelAmt() + "\n")
        else
            file.appendText("S:" + "\n")

        file.appendText("cEditor\n")
        if (Inst.cEditor.getStartLabel() != null)
            file.appendText("C:" + Inst.cEditor.getStartLabel()?.toolTipText + ":" + Inst.cEditor.getStartLabelAmt() + "\n")
        else
            file.appendText("C:" + "\n")

        for ((labelNum, label) in Inst.cEditor.getDecomposeLabels().withIndex()){
            file.appendText("$labelNum:" + label.toolTipText + ":" + Inst.cEditor.getDecomposeLabelAmt(labelNum) + ":" + Inst.cEditor.getDecomposeLabelPctg(labelNum) + "\n")
        }
    }

    fun reloadall(){

    }
}