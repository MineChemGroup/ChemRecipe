package main.kotlin.search

import main.kotlin.misc.Inst
import main.kotlin.misc.Inst.copyHandler
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

class ListSearch : KeyAdapter() {
    override fun keyPressed(event: KeyEvent) {
        val ch: Char = event.keyChar
        var text = Inst.right.listSearchBar.text //+ ch

        if (event.keyCode == 8)
            text = text.dropLast(1)
        else
            text += ch

        Inst.right.text = text

        Inst.right.demoList.removeAllElements()

        if (text.isBlank()){
            for (item in Inst.right.finalList)
                Inst.right.demoList.addElement(item)

            Inst.right.jPanel.revalidate()
            return
        }

        for (item in Inst.right.finalList)
            if (item.contains(text, ignoreCase = true))
                Inst.right.demoList.addElement(item)

        Inst.right.jPanel.revalidate()
    }
}