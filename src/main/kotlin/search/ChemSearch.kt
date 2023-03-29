package main.kotlin.search

import main.kotlin.misc.Inst
import main.kotlin.misc.Inst.copyHandler
import java.awt.Dimension
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import kotlin.math.ceil
import kotlin.math.floor

class ChemSearch : KeyAdapter() {

    companion object {
        var text: String = ""
    }
    override fun keyPressed(event: KeyEvent) {
        val ch: Char = event.keyChar
        text = Inst.left.chemSearchBar.text //+ ch

        if (event.keyCode == 8)
            text = text.dropLast(1)
        else
            text += ch

        Inst.left.chemassetpanel.removeAll()

        if (text.isBlank()){
            for (asset in Inst.left.listchemassets)
                Inst.left.chemassetpanel.add(asset.copyHandler())

            Inst.left.changeSizeDynamically(Inst.left.chemassetpanel)
            Inst.left.refreshChems()
            return
        }

        for (asset in Inst.left.listchemassets)
            if (asset.toolTipText.contains(text, ignoreCase = true))
                Inst.left.chemassetpanel.add(asset.copyHandler())

        Inst.left.changeSizeDynamically(Inst.left.chemassetpanel)
        Inst.left.refreshChems()
    }
}