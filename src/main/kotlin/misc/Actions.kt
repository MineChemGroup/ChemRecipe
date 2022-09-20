package misc

import com.formdev.flatlaf.FlatDarculaLaf
import com.formdev.flatlaf.FlatIntelliJLaf
import com.formdev.flatlaf.IntelliJTheme
import com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatSolarizedDarkIJTheme
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatSolarizedLightContrastIJTheme
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatSolarizedLightIJTheme
import java.awt.Desktop
import java.awt.event.ActionEvent
import java.awt.event.ActionListener


class Actions : ActionListener {
    override fun actionPerformed(e: ActionEvent) {
        when(e.source) {
            Inst.menu.light -> {
                FlatIntelliJLaf.setup()
                Inst.refresh() }
            Inst.menu.solarized -> {
                FlatSolarizedLightIJTheme.setup()
                Inst.refresh() }
            Inst.menu.nord -> {
                FlatNordIJTheme.setup()
                Inst.refresh() }
            Inst.menu.deepocean -> {
                FlatGradiantoDeepOceanIJTheme.setup()
                Inst.refresh() }
            Inst.menu.dark -> {
                FlatDarculaLaf.setup();
                Inst.refresh() }
            Inst.mEditor.createnew -> {

            }
            Inst.right.openfolder ->{
                Desktop.getDesktop().open(Inst.loader.recipeFolder)
            }
        }
    }
}