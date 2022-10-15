package panels

import actions.CEditorActions
import misc.Inst
import transfer.CEditorHandler
import java.awt.*
import java.io.File
import javax.swing.*


class CEditor(val jPanel: JPanel = JPanel()) {

    val resultLabel = JLabel()
    val layeredpane1 = JLayeredPane()
    lateinit var listpanel : JPanel
    lateinit var scrollableArea0 : JScrollPane

    fun reset(){
        layeredpane1.remove(layeredpane1.getComponentsInLayer(20)[0])
        layeredpane1.remove(layeredpane1.getComponentsInLayer(21)[0])
        listpanel.removeAll()
    }

    fun add(label : JLabel){
        println(1)
        if (resultLabel.mousePosition != null) {
            val i = resultLabel
            label.bounds = Rectangle(i.bounds.x + 10, i.bounds.y, 32, 32)
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
                }
                Inst.refresh()
            }
        } else {
            println(2)

            val listlabel = JLabel()
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
                }
                Inst.refresh()
            }
            listlabel.add(spinner)

            val slider = JSlider(JSlider.HORIZONTAL, 0, 100, 100)
            slider.minorTickSpacing = 5
            slider.majorTickSpacing = 10
            slider.paintTicks = true
            slider.paintLabels = true
            listlabel.add(slider)

            val c = GridBagConstraints()
            c.fill = GridBagConstraints.HORIZONTAL
            c.gridx = 0
            if (c.gridy == 0 || c.gridy == -1)
                c.anchor = GridBagConstraints.FIRST_LINE_START
            c.gridwidth = GridBagConstraints.REMAINDER
            c.weightx = 1.0
            c.weighty = 1.0
            c.gridheight = 44

            //listpanel.add(listlabel)
            listpanel.add(label, c)

            println(3)
        }
        Inst.refresh()
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
        listpanel.maximumSize = Dimension(220,300)
        listpanel.preferredSize = Dimension(220,300)
        listpanel.addMouseListener(CEditorActions())
        listpanel.transferHandler = CEditorHandler()
        listpanel.isVisible = true
        listpanel.layout = GridBagLayout()
        scrollableArea0 = JScrollPane(listpanel).apply { preferredSize = Dimension(340,200) }
        scrollableArea0.verticalScrollBar.unitIncrement = 16
        scrollableArea0.verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        scrollableArea0.horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        jPanel.add(scrollableArea0)
    }
}