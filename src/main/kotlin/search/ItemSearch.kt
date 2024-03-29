package main.kotlin.search

import main.kotlin.misc.Inst
import main.kotlin.misc.Inst.copyHandler
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

class ItemSearch : KeyAdapter() {
    override fun keyPressed(event: KeyEvent) {
        val ch: Char = event.keyChar
        var text = Inst.left.itemSearchBar.text //+ ch

        if (event.keyCode == 8)
            text = text.dropLast(1)
        else
            text += ch

        Inst.left.mcassetpanel.removeAll()

        if (text.isBlank()){
            for (asset in Inst.left.listmcassets)
                Inst.left.mcassetpanel.add(asset.copyHandler())

            Inst.left.changeSizeDynamically(Inst.left.mcassetpanel)
            Inst.left.refreshItems()
            return
        }

        for (asset in Inst.left.listmcassets)
            if (asset.toolTipText.contains(text, ignoreCase = true))
                Inst.left.mcassetpanel.add(asset.copyHandler())

        Inst.left.changeSizeDynamically(Inst.left.mcassetpanel)
        Inst.left.refreshItems()
    }
}