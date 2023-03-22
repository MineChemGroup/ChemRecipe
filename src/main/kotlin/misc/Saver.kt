package misc

import java.io.File
import java.io.PrintWriter
import java.nio.file.Files
import java.nio.file.Paths
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
            val newNum = labelNum + 10
            file.appendText("$newNum:" + label.toolTipText + ":" + Inst.cEditor.getDecomposeLabelAmt(labelNum) + ":" + Inst.cEditor.getDecomposeLabelPctg(labelNum) + "\n")
        }
    }

    fun save(listIndex : Int){

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
            val newNum = labelNum + 10
            file.appendText("$newNum:" + label.toolTipText + ":" + Inst.cEditor.getDecomposeLabelAmt(labelNum) + ":" + Inst.cEditor.getDecomposeLabelPctg(labelNum) + "\n")
        }
    }

    fun reloadall(){
        Inst.right.demoList.removeAllElements()
        Files.walk(Paths.get(Inst.loader.recipeFolder.path)).use {
                paths -> paths.filter { Files.isRegularFile(it) }
            .forEach {
                Inst.right.demoList.addElement(it.fileName.toString().split(".chemrecipe")[0])
            }
        }
    }
}