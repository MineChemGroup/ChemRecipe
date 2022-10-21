package panels

import actions.SEditorActions
import misc.Inst
import transfer.SEditorHandler
import java.awt.*
import java.io.File
import javax.swing.*


class SEditor(val jPanel: JPanel = JPanel()) {

    val defaultlabels = arrayListOf<JLabel>()

    val mirror = JButton("Mirror recipe").apply { alignmentX = 0.5f }

    lateinit var upperlayeredpane : JLayeredPane
    lateinit var lowerlayeredpane : JLayeredPane

    fun getResultLabel() : JLabel?{
        if (lowerlayeredpane.getComponentCountInLayer(20) != 0){
            return lowerlayeredpane.getComponentsInLayer(20)[0] as JLabel
        } else {
            return null
        }
    }

    fun getResultLabelAmt() : Int{
        return (lowerlayeredpane.getComponentsInLayer(21)[0] as JSpinner).value as Int
    }

    fun getSynthesisLabels() : HashMap<Int, JLabel>{
        val map = hashMapOf<Int, JLabel>()
        for (id in 1..9){
            if (upperlayeredpane.getComponentCountInLayer(10+id) > 0){
                map[id] = upperlayeredpane.getComponentsInLayer(10+id)[0] as JLabel
            }
        }
        return map
    }

    fun getSynthesisLabelAmt(id : Int) : Int?{
        for (part0 in upperlayeredpane.getComponentsInLayer(21)){
            val part1 = part0 as JSpinner
            if ((part1.getClientProperty("connected") as Int - 10) == id )
                return part1.value as Int
        }
        return null
    }

    fun reset(){
        for (i in 11..21){
            for (s in upperlayeredpane.getComponentsInLayer(i)){
                upperlayeredpane.remove(s)
            }
            for (s in lowerlayeredpane.getComponentsInLayer(i)){
                lowerlayeredpane.remove(s)
            }
        }
    }

    fun add(label : JLabel){
        for(i in defaultlabels){
            if (i.mousePosition != null){
                val id = i.getClientProperty("number") as Int

                label.bounds = Rectangle(i.bounds.x+10, i.bounds.y, 32,32)
                label.putClientProperty("number", id)

                label.addMouseWheelListener { e ->
                    val l = e.source as JLabel
                    val idl = l.getClientProperty("number") as Int
                    if (idl < 10){
                        for (s in upperlayeredpane.getComponentsInLayer(21)){
                            if (((s as JSpinner).getClientProperty("connected") as Int)-10 == idl){
                                s.value = (s.value as Int + -e.wheelRotation)
                                break
                            }
                        }
                    } else {
                        if (lowerlayeredpane.getComponentCountInLayer(21) > 0) {
                            val s = lowerlayeredpane.getComponentsInLayer(21)[0] as JSpinner
                            s.value = (s.value as Int + -e.wheelRotation)
                        }
                    }
                }

                when (id) {
                    10 -> {
                        lowerlayeredpane.add(label,Integer(20))
                        lowerlayeredpane.setLayer(label, 20)
                    }
                    else -> {
                        upperlayeredpane.add(label,Integer(10+id))
                        upperlayeredpane.setLayer(label, 10+id)
                    }
                }

                val spinner = JSpinner()
                spinner.setBounds(i.bounds.x-12, i.bounds.y+28, 60,32)
                spinner.value = 1
                spinner.putClientProperty("connected", 10+id)

                if (id < 10) {
                    upperlayeredpane.add(spinner, Integer(21))
                }else {
                    lowerlayeredpane.add(spinner, Integer(21))
                    lowerlayeredpane.setLayer(spinner, 21)
                }
                spinner.addChangeListener { e ->
                    val num : Int = (e.source as JSpinner).value as Int

                    if (num > 64){
                        (e.source as JSpinner).value = 64
                    } else if (num < 1){
                        val layer = (e.source as JSpinner).getClientProperty("connected") as Int

                        if (layer-10 < 10) {
                            upperlayeredpane.remove(upperlayeredpane.getComponentsInLayer(layer)[0])
                            upperlayeredpane.remove(e.source as JSpinner)
                        }else {
                            lowerlayeredpane.remove(lowerlayeredpane.getComponentsInLayer(layer)[0])
                            lowerlayeredpane.remove(e.source as JSpinner)
                        }
                    }
                    Inst.refresh()
                }

                Inst.refresh()
                break
            }
        }
    }

    fun removeSpinner(id : Int){
        if (id < 10) {
            if (upperlayeredpane.getComponentCountInLayer(21) > 0) {
                for (comp in upperlayeredpane.getComponentsInLayer(21)) {
                    if (((comp as JSpinner).getClientProperty("connected") as Int) == id + 10) {
                        upperlayeredpane.remove(comp as JSpinner)
                        break
                    }
                }
            }
        }else {
            if (lowerlayeredpane.getComponentCountInLayer(21) > 0) {
                val s = lowerlayeredpane.getComponentsInLayer(21)[0] as JSpinner ?: return
                lowerlayeredpane.remove(s)
            }
        }
    }

    fun init(){
        jPanel.layout = BoxLayout(jPanel, BoxLayout.PAGE_AXIS)

        upperlayeredpane = JLayeredPane()

        val jLabel = JLabel(ImageIcon(File(Inst.loader.baseFolder.path + "/3x3.png").path))/*.apply { minimumSize = Dimension(160,160); alignmentX = 0.5f; alignmentY = 0.2f}*/
        jLabel.setBounds(0,0,240,240)
        upperlayeredpane.add(jLabel, JLayeredPane.DEFAULT_LAYER)

        val diff = 78
        var x = 0
        var y = 0
        for (i in 1..9){

            val label = JLabel()
            label.background = Color(139,139,139)
            defaultlabels.add(label)
            label.addMouseListener(SEditorActions())
            label.transferHandler = SEditorHandler()
            label.putClientProperty("number", i)
            label.isVisible = true
            label.isOpaque = true
            upperlayeredpane.add(label, Integer(i))

            if (x == diff*3) {
                x = 0
                y += diff
            }
            label.setBounds(x+18, y+18, 44,44)
            x += diff
        }
        upperlayeredpane.isVisible = true
        val secondpanel = JPanel(GridLayout(1,1)).apply { alignmentX = 0.5f }
        secondpanel.add(upperlayeredpane)
        //secondpanel.alignmentX = 0.5f
        secondpanel.minimumSize = Dimension(240,240)
        secondpanel.maximumSize = Dimension(240,240)
        secondpanel.preferredSize = Dimension(240,240)

        val mainPanel = JPanel()
        mainPanel.layout = BoxLayout(mainPanel, BoxLayout.PAGE_AXIS)
        mainPanel.add(Box.createVerticalStrut(40))
        mainPanel.add(secondpanel)
        mainPanel.add(Box.createVerticalStrut(10))
        val arrow = JLabel(ImageIcon(File(Inst.loader.baseFolder.path + "/arrow.png").path)).apply { alignmentX = 0.5f }
        mainPanel.add(arrow)

        mainPanel.add(Box.createVerticalStrut(10))

        lowerlayeredpane = JLayeredPane()
        lowerlayeredpane.setBounds(120,20,100,100)
        val resultLabel = JLabel()
        resultLabel.background = Color(139,139,139)
        defaultlabels.add(resultLabel)
        resultLabel.addMouseListener(SEditorActions())
        resultLabel.transferHandler = SEditorHandler()
        resultLabel.putClientProperty("number", 10)
        resultLabel.isVisible = true
        resultLabel.isOpaque = true
        resultLabel.setBounds(37,18,44,44)
        val oneslot = JLabel(ImageIcon(File(Inst.loader.baseFolder.path + "/oneslot.png").path)).apply { alignmentX = Component.CENTER_ALIGNMENT }
        oneslot.setBounds(18,0,78,78)
        lowerlayeredpane.add(oneslot, JLayeredPane.DEFAULT_LAYER)
        lowerlayeredpane.add(resultLabel, Integer(10))
        val thirdpanel = JPanel(GridLayout(1,1)).apply { alignmentX = Component.CENTER_ALIGNMENT }
        thirdpanel.add(lowerlayeredpane)
        thirdpanel.minimumSize = Dimension(120,120)
        thirdpanel.maximumSize = Dimension(120,120)
        thirdpanel.preferredSize = Dimension(120,120)

        mainPanel.add(thirdpanel)

        jPanel.add(mainPanel)

        val fourthpanel = JPanel(GridLayout(1,1)).apply { alignmentX = 0.5f }
        mirror.addActionListener(Inst.buttonActions)
        mirror.apply { preferredSize = Dimension(175,50); font = Inst.font; toolTipText = "Copies recipe into decomposer tab" }
        fourthpanel.minimumSize = Dimension(175,50)
        fourthpanel.maximumSize = Dimension(175,50)
        fourthpanel.add(mirror)

        jPanel.add(fourthpanel)
        jPanel.revalidate()
    }
}