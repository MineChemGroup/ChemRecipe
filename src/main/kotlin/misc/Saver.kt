package main.kotlin.misc

import main.kotlin.tooltip.CustomLabel
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.io.PrintWriter
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import javax.swing.JOptionPane


object Saver {

    fun save(){
        val listIndex = Inst.right.list.selectedIndex

        if (listIndex == -1)
            JOptionPane.showMessageDialog(null, "Please select a recipe")

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
                file.appendText(labelNum.toString() + ":" + Inst.sEditor.getSynthesisLabels()[labelNum]?.info
                        + ":" + Inst.sEditor.getSynthesisLabelAmt(labelNum) + "\n")
            else
                file.appendText("$labelNum:\n")
        }
        if (Inst.sEditor.getResultLabel() != null)
            file.appendText("S:" + Inst.sEditor.getResultLabel()?.info + ":" + Inst.sEditor.getResultLabelAmt() + "\n")
        else
            file.appendText("S:" + "\n")

        file.appendText("cEditor\n")
        if (Inst.cEditor.getStartLabel() != null)
            file.appendText("C:" + Inst.cEditor.getStartLabel()?.info + ":" + Inst.cEditor.getStartLabelAmt() + "\n")
        else
            file.appendText("C:" + "\n")

        for ((labelNum, label) in Inst.cEditor.getDecomposeLabels().withIndex()){
            val newNum = labelNum + 10
            file.appendText("$newNum:" + label.info + ":" + Inst.cEditor.getDecomposeLabelAmt(labelNum) + ":" + Inst.cEditor.getDecomposeLabelPctg(labelNum) + "\n")
        }

        compoundLoadCheck(file.toPath())
        enoughCompoundsCheck()
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
                file.appendText(labelNum.toString() + ":" + Inst.sEditor.getSynthesisLabels()[labelNum]?.info
                        + ":" + Inst.sEditor.getSynthesisLabelAmt(labelNum) + "\n")
            else
                file.appendText("$labelNum:\n")
        }
        if (Inst.sEditor.getResultLabel() != null)
            file.appendText("S:" + Inst.sEditor.getResultLabel()?.info + ":" + Inst.sEditor.getResultLabelAmt() + "\n")
        else
            file.appendText("S:" + "\n")

        file.appendText("cEditor\n")
        if (Inst.cEditor.getStartLabel() != null)
            file.appendText("C:" + Inst.cEditor.getStartLabel()?.info + ":" + Inst.cEditor.getStartLabelAmt() + "\n")
        else
            file.appendText("C:" + "\n")

        for ((labelNum, label) in Inst.cEditor.getDecomposeLabels().withIndex()){
            val newNum = labelNum + 10
            file.appendText("$newNum:" + label.info + ":" + Inst.cEditor.getDecomposeLabelAmt(labelNum) + ":" + Inst.cEditor.getDecomposeLabelPctg(labelNum) + "\n")
        }

        compoundLoadCheck(file.toPath())
        enoughCompoundsCheck()
    }

    fun reloadall(){
        Inst.right.list.removeAll()
        Inst.right.demoList.removeAllElements()
        Files.walk(Paths.get(Inst.loader.recipeFolder.path)).use {
                paths -> paths.filter { Files.isRegularFile(it) }
            .forEach {
                val recipeName = it.fileName.toString().split(".chemrecipe")[0]
                Inst.right.demoList.addElement(recipeName)

                compoundLoadCheck(it)
                /*
                val cLine : String = Files.lines(it).use { lines -> lines.skip(10).findFirst().get() }
                if (cLine == "S:")
                    return@forEach

                val parts = cLine.split(":")
                if (!parts[1].contains("Compound"))
                    return@forEach

                if (recipeName.contains("New Recipe"))
                    return@forEach

                for (compound in Inst.left.listchemassets)
                    if (compound.info == parts[1])
                        compound.toolTipText = recipeName

                for (compound in Inst.left.chemassetpanel.components)
                    if ((compound as CustomLabel).info == parts[1])
                        compound.toolTipText = recipeName
                */
            }
        }
        enoughCompoundsCheck()
    }

    fun compoundLoadCheck(path: Path){
        val recipeName = path.fileName.toString().split(".chemrecipe")[0]
        val cLine : String = Files.lines(path).use { lines -> lines.skip(10).findFirst().get() }
        if (cLine == "S:")
            return

        val parts = cLine.split(":")
        if (!parts[1].contains("Compound"))
            return

        if (recipeName.contains("New Recipe"))
            return

        for (compound in Inst.left.listchemassets) {
            if (compound.toolTipText == recipeName)
                compound.toolTipText = "Undefined " + compound.info
            if (compound.info == parts[1])
                compound.toolTipText = recipeName
        }
        for (compound in Inst.left.chemassetpanel.components) {
            if ((compound as CustomLabel).toolTipText == recipeName)
                compound.toolTipText = "Undefined " + compound.info
            if ((compound as CustomLabel).info == parts[1])
                compound.toolTipText = recipeName
        }

        val clabel = Inst.cEditor.getStartLabel()
        if (clabel != null)
            if (clabel.info == parts[1])
                clabel.toolTipText = recipeName

        val slabel = Inst.sEditor.getResultLabel()
        if (slabel != null)
            if (slabel.info == parts[1])
                slabel.toolTipText = recipeName

        enoughCompoundsCheck()
    }

    fun compoundDeleteCheck(path: Path){
        val recipeName = path.fileName.toString().split(".chemrecipe")[0]
        if (isEmptyFile(path))
            return

        val cLine : String = Files.lines(path).use { lines -> lines.skip(10).findFirst().get() }
        if (cLine == "S:")
            return

        val parts = cLine.split(":")
        if (!parts[1].contains("Compound"))
            return

        if (recipeName.contains("New Recipe"))
            return

        for (compound in Inst.left.listchemassets)
            if (compound.info == parts[1])
                compound.toolTipText = "Undefined Compound " + parts[1].replace("Compound ", "")

        for (compound in Inst.left.chemassetpanel.components)
            if ((compound as CustomLabel).info == parts[1])
                compound.toolTipText = "Undefined Compound " + parts[1].replace("Compound ", "")
    }

    private fun isEmptyFile(path: Path): Boolean {
        return path.toFile().readLines().isEmpty()
    }

    var compoundsChecking = false
    fun enoughCompoundsCheck(){
        if (compoundsChecking)
            return

        compoundsChecking = true

        var a = 0
        var b = 0
        for (compound in Inst.left.listchemassets){
            if (compound.toolTipText.contains("Element"))
                continue

            if (compound.toolTipText.contains("Undefined"))
                a++
            else
                b++
        }

        //println("a: $a")
        //println("b: $b")

        if (a < 5){
            for (i in a+b+1..a+(5-a)+b){
                Painter.paintOne(Inst.loader.compoundsFolder.toPath(), i)
                Inst.left.loadCompound(i)
            }
        }
        Inst.left.changeSizeDynamically(Inst.left.chemassetpanel)
        Inst.left.refreshChems()
        compoundsChecking = false
    }
}