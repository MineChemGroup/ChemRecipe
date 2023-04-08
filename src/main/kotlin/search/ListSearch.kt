package main.kotlin.search

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import main.kotlin.misc.Inst
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.SwingUtilities

class ListSearch : KeyAdapter() {
    override fun keyPressed(event: KeyEvent) {
        val ch: Char = event.keyChar
        var text = Inst.right.listSearchBar.text //+ ch

        if (event.keyCode == 8)
            text = text.dropLast(1)
        else
            text += ch

        Inst.right.text = text
        SwingUtilities.updateComponentTreeUI(Inst.right.list)
    }
}