package main.kotlin.list

import main.kotlin.misc.Inst
import main.kotlin.tooltip.ItemLabel
import java.awt.Component
import java.awt.Dimension
import javax.swing.JLabel
import javax.swing.JList
import javax.swing.ListCellRenderer

class CustomCellRenderer : ListCellRenderer<String>{

    override fun getListCellRendererComponent(list: JList<out String>?, value: String?, index: Int, isSelected: Boolean, cellHasFocus: Boolean): Component {

        lateinit var label : JLabel

        if (value!!.contains(Inst.right.text, true) || Inst.right.text.isBlank()){
            label = ItemLabel(value)
            label.text = value
            label.preferredSize = Dimension(100, 28)
            label.maximumSize = Dimension(1500, 28)
        } else {
            label = JLabel()
            label.isVisible = false
        }

        label.isOpaque = true

        if (isSelected){
            label.background = list?.selectionBackground
            label.foreground = list?.selectionForeground
        } else {
            label.background = list?.background
            label.foreground = list?.foreground
        }

        label.font = Inst.font
        return label
    }
}