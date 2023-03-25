package main.kotlin.panels

import main.kotlin.actions.CEditorActions
import main.kotlin.actions.LeftMouseActions
import main.kotlin.misc.Inst
import main.kotlin.tooltip.CustomLabel
import transfer.CEditorHandler
import java.awt.*
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import java.io.File
import javax.swing.*


class CEditor(val jPanel: JPanel = JPanel()) {

    val resultLabel = JLabel()
    val layeredpane1 = JLayeredPane()
    lateinit var listpanel : JPanel
    lateinit var scrollableArea0 : JScrollPane

    fun hasLabelThenIncrement(label : CustomLabel, spinnerNum: Int) : Boolean{
        for (part0 in listpanel.components){
            val part1 = part0 as JLabel
            val part2 = part1.components[0] as CustomLabel

            if (part2.info == label.info){
                val part3 = part1.components[1] as JSpinner
                part3.value = part3.value as Int + spinnerNum
                return true
            }
        }
        return false
    }

    fun getStartLabel() : CustomLabel? {
        return if (layeredpane1.getComponentCountInLayer(20) != 0){
            layeredpane1.getComponentsInLayer(20)[0] as CustomLabel
        } else {
            null
        }
    }

    fun getStartLabelAmt() : Int{
        return (layeredpane1.getComponentsInLayer(21)[0] as JSpinner).value as Int
    }

    fun getDecomposeLabels() : ArrayList<CustomLabel>{
        val list = arrayListOf<CustomLabel>()
        for (part0 in listpanel.components){
            val part1 = part0 as JLabel
            list.add(part1.getComponent(0) as CustomLabel)
        }
        return list
    }

    fun getDecomposeLabelAmt(id : Int) : Int{
        val part1 = listpanel.components[id] as JLabel
        val part2 = part1.components[1] as JSpinner
        return part2.value as Int
    }

    fun getDecomposeLabelPctg(id : Int) : Int{
        val part1 = listpanel.components[id] as JLabel
        val part3 = part1.components[2] as JSlider
        return part3.value
    }

    fun reset(){
        if (layeredpane1.getComponentCountInLayer(20) > 0)
            layeredpane1.remove(layeredpane1.getComponentsInLayer(20)[0])
        if (layeredpane1.getComponentCountInLayer(21) > 0)
            layeredpane1.remove(layeredpane1.getComponentsInLayer(21)[0])
        listpanel.preferredSize = Dimension(jPanel.width, 108+54*listpanel.componentCount)
        listpanel.removeAll()
    }

    fun add(label : JLabel){
        if (resultLabel.mousePosition != null) {
            val i = resultLabel
            label.bounds = Rectangle(i.bounds.x + 10, i.bounds.y, 32, 32)

            label.transferHandler = CEditorHandler()
            label.removeMouseListener(LeftMouseActions())
            label.addMouseListener(CEditorActions())

            layeredpane1.add(label, Integer(20))
            layeredpane1.setLayer(label, 20)
            val spinner = JSpinner()
            spinner.setBounds(i.bounds.x - 12, i.bounds.y + 28, 60, 32)
            spinner.value = 1
            layeredpane1.add(spinner, Integer(21))
            layeredpane1.setLayer(spinner, 21)
            label.addMouseWheelListener { e ->
                val s = layeredpane1.getComponentsInLayer(21)[0] as JSpinner
                s.value = (s.value as Int + -e.wheelRotation)
            }
            spinner.addChangeListener { e ->
                val num: Int = (e.source as JSpinner).value as Int
                if (num > 64) {
                    (e.source as JSpinner).value = 64
                } else if (num < 1) {
                    layeredpane1.remove(layeredpane1.getComponentsInLayer(20)[0])
                    layeredpane1.remove(e.source as JSpinner)
                    Inst.refresh()
                }
            }
        } else {

            val listlabel = JLabel()

            listlabel.transferHandler = CEditorHandler()
            listlabel.removeMouseListener(LeftMouseActions())
            listlabel.addMouseListener(CEditorActions())

            listlabel.minimumSize = Dimension(listpanel.size.width, 44)
            listlabel.preferredSize = Dimension(listpanel.size.width, 44)
            listlabel.maximumSize = Dimension(listpanel.size.width, 44)
            listlabel.layout = FlowLayout()
            listlabel.add(label)

            val spinner = JSpinner()
            spinner.value = 1
            spinner.addChangeListener { e ->
                val num: Int = (e.source as JSpinner).value as Int
                if (num > 64) {
                    (e.source as JSpinner).value = 64
                } else if (num < 1) {
                    for (i in listpanel.components){
                        for(j in (i as JLabel).components){
                            if (j == e.source){
                                listpanel.remove(i)
                            }
                        }
                    }
                    listpanel.preferredSize = Dimension(jPanel.width, 108+54*listpanel.componentCount)
                }
                Inst.refresh()
            }
            listlabel.add(spinner)

            listlabel.addMouseWheelListener { e ->
                for (s in listpanel.components){
                    if (s == e.source){
                        val slabel = s as JLabel
                        val sspinner = slabel.getComponent(1) as JSpinner
                        sspinner.value = (sspinner.value as Int + -e.wheelRotation)
                        break
                    }
                }
            }

            val slider = JSlider(JSlider.HORIZONTAL, 0, 100, 100)
            slider.minorTickSpacing = 10
            slider.majorTickSpacing = 20
            slider.paintTicks = true
            slider.paintLabels = true
            listlabel.add(slider)

            val c = GridBagConstraints()
            c.fill = GridBagConstraints.HORIZONTAL
            c.gridx = 0
            //c.anchor = GridBagConstraints.FIRST_LINE_START
            c.gridwidth = GridBagConstraints.REMAINDER
            c.weightx = 1.0
            //c.weighty = 1.0
            c.gridheight = 55
            c.ipady = 10
            listpanel.add(listlabel, c)
            //listpanel.add(label, c)

            listpanel.preferredSize = Dimension(jPanel.width, 108+54*listpanel.componentCount)
        }
        Inst.refresh()
    }

    fun add(label : JLabel, position : Int, spinnerNum : Int, sliderNum : Int) {
        if (position == 0) {
            val i = resultLabel
            label.bounds = Rectangle(i.bounds.x + 10, i.bounds.y, 32, 32)

            label.transferHandler = CEditorHandler()
            label.removeMouseListener(LeftMouseActions())
            label.addMouseListener(CEditorActions())

            layeredpane1.add(label, Integer(20))
            layeredpane1.setLayer(label, 20)
            val spinner = JSpinner()
            spinner.setBounds(i.bounds.x - 12, i.bounds.y + 28, 60, 32)
            spinner.value = spinnerNum
            layeredpane1.add(spinner, Integer(21))
            layeredpane1.setLayer(spinner, 21)
            label.addMouseWheelListener { e ->
                val s = layeredpane1.getComponentsInLayer(21)[0] as JSpinner
                s.value = (s.value as Int + -e.wheelRotation)
            }
            spinner.addChangeListener { e ->
                val num: Int = (e.source as JSpinner).value as Int
                if (num > 64) {
                    (e.source as JSpinner).value = 64
                } else if (num < 1) {
                    layeredpane1.remove(layeredpane1.getComponentsInLayer(20)[0])
                    layeredpane1.remove(e.source as JSpinner)
                    Inst.refresh()
                }
            }
        } else {
            val listlabel = JLabel()

            listlabel.transferHandler = CEditorHandler()
            listlabel.removeMouseListener(LeftMouseActions())
            listlabel.addMouseListener(CEditorActions())

            listlabel.minimumSize = Dimension(listpanel.size.width, 44)
            listlabel.preferredSize = Dimension(listpanel.size.width, 44)
            listlabel.maximumSize = Dimension(listpanel.size.width, 44)
            listlabel.layout = FlowLayout()
            listlabel.add(label)

            val spinner = JSpinner()
            spinner.value = spinnerNum
            spinner.addChangeListener { e ->
                val num: Int = (e.source as JSpinner).value as Int
                if (num > 64) {
                    (e.source as JSpinner).value = 64
                } else if (num < 1) {
                    for (i in listpanel.components) {
                        for (j in (i as JLabel).components) {
                            if (j == e.source) {
                                listpanel.remove(i)
                            }
                        }
                    }
                    listpanel.preferredSize = Dimension(jPanel.width, 108 + 54 * listpanel.componentCount)
                }
                Inst.refresh()
            }
            listlabel.add(spinner)

            listlabel.addMouseWheelListener { e ->
                for (s in listpanel.components) {
                    if (s == e.source) {
                        val slabel = s as JLabel
                        val sspinner = slabel.getComponent(1) as JSpinner
                        sspinner.value = (sspinner.value as Int + -e.wheelRotation)
                        break
                    }
                }
            }

            val slider = JSlider(JSlider.HORIZONTAL, 0, 100, sliderNum)
            slider.minorTickSpacing = 10
            slider.majorTickSpacing = 20
            slider.paintTicks = true
            slider.paintLabels = true
            listlabel.add(slider)

            val c = GridBagConstraints()
            c.fill = GridBagConstraints.HORIZONTAL
            c.gridx = 0
            //c.anchor = GridBagConstraints.FIRST_LINE_START
            c.gridwidth = GridBagConstraints.REMAINDER
            c.weightx = 1.0
            //c.weighty = 1.0
            c.gridheight = 55
            c.ipady = 10
            listpanel.add(listlabel, c)
            //listpanel.add(label, c)

            listpanel.preferredSize = Dimension(jPanel.width, 108 + 54 * listpanel.componentCount)
        }
    }

    fun init(){

        jPanel.layout = BoxLayout(jPanel, BoxLayout.PAGE_AXIS)

        jPanel.add(Box.createVerticalStrut(40))

        layeredpane1.setBounds(120,20,100,100)
        resultLabel.background = Color(139,139,139)
        resultLabel.addMouseListener(CEditorActions())
        resultLabel.transferHandler = CEditorHandler()
        resultLabel.isVisible = true
        resultLabel.isOpaque = true
        resultLabel.putClientProperty("number", 11)
        resultLabel.setBounds(32,18,44,44)
        val oneslot = JLabel(ImageIcon(File(Inst.loader.baseFolder.path + "/oneslot.png").path)).apply { alignmentX = Component.CENTER_ALIGNMENT }
        oneslot.setBounds(12,0,78,78)
        layeredpane1.add(oneslot, JLayeredPane.DEFAULT_LAYER)
        layeredpane1.add(resultLabel, Integer(10))
        val firstpanel = JPanel(GridLayout(1,1)).apply { alignmentX = Component.CENTER_ALIGNMENT }
        firstpanel.add(layeredpane1)
        firstpanel.minimumSize = Dimension(100,100)
        firstpanel.maximumSize = Dimension(100,100)
        firstpanel.preferredSize = Dimension(100,100)

        jPanel.add(firstpanel)

        val arrowPanel = JPanel()
        arrowPanel.minimumSize = Dimension(100,100)
        arrowPanel.maximumSize = Dimension(100,100)
        arrowPanel.preferredSize = Dimension(100,100)
        arrowPanel.layout = BoxLayout(arrowPanel, BoxLayout.PAGE_AXIS)
        val arrow = JLabel(ImageIcon(File(Inst.loader.baseFolder.path + "/arrow.png").path)).apply { alignmentX = 0.5f }
        arrowPanel.add(arrow)

        jPanel.add(arrowPanel)

        listpanel = JPanel()
        listpanel.minimumSize = Dimension(220,300)
        //listpanel.maximumSize = Dimension(220,300)
        listpanel.preferredSize = Dimension(220,300)
        listpanel.addMouseListener(CEditorActions())
        listpanel.transferHandler = CEditorHandler()
        listpanel.isVisible = true
        listpanel.layout = GridBagLayout()
        scrollableArea0 = JScrollPane(listpanel).apply { preferredSize = Dimension(340,200) }
        scrollableArea0.verticalScrollBar.unitIncrement = 16
        scrollableArea0.verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        scrollableArea0.horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER

        Inst.jframe.addComponentListener(object : ComponentAdapter() {
            override fun componentResized(e: ComponentEvent) {

                val width = (Inst.jframe.width/3)-12
                scrollableArea0.preferredSize = Dimension(width, Inst.jframe.height-255)
                //Inst.left.mcassetpanel.preferredSize = Dimension(width, (1026*32*32)/(width).toInt())
                Inst.left.mcassetpanel.preferredSize = Dimension(width, 1026*32*32/(width*0.68).toInt())
                //Inst.left.chemassetpanel.preferredSize = Dimension(width, (240*32*32)/(width).toInt())
                Inst.left.chemassetpanel.preferredSize = Dimension(width, 240*32*32/(width*0.68).toInt())
            }
        })

        jPanel.add(scrollableArea0)
    }
}