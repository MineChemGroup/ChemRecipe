package main.kotlin.search

import main.kotlin.misc.Inst
import main.kotlin.misc.Inst.copyHandler
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

class ChemSearch : KeyAdapter() {
    override fun keyPressed(event: KeyEvent) {
        val ch: Char = event.keyChar
        var text = Inst.left.chemSearchBar.text //+ ch

        if (event.keyCode == 8)
            text = text.dropLast(1)
        else
            text += ch

        Inst.left.chemassetpanel.removeAll()

        if (text.isBlank()){
            for (asset in Inst.left.listchemassets)
                Inst.left.chemassetpanel.add(asset.copyHandler())

            Inst.left.refreshChems()
            return
        }

        for (asset in Inst.left.listchemassets)
            if (asset.toolTipText.contains(text, ignoreCase = true))
                Inst.left.chemassetpanel.add(asset.copyHandler())

        Inst.left.refreshChems()
    }
}