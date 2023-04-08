package main.kotlin.list;

import javax.swing.*;
import javax.swing.plaf.basic.BasicListUI;
import java.awt.*;

public class CustomUI extends BasicListUI {

    @Override
    protected void paintCell(Graphics g, int row, Rectangle rowBounds, ListCellRenderer cellRenderer, ListModel dataModel, ListSelectionModel selModel, int leadIndex){

        Object value = dataModel.getElementAt(row);
        boolean cellHasFocus = list.hasFocus() && (row == leadIndex);
        boolean isSelected = selModel.isSelectedIndex(row);

        Component rendererComponent = cellRenderer.getListCellRendererComponent(list, value, row, isSelected, cellHasFocus);

        int cx = rowBounds.x;
        int cy = rowBounds.y;
        int cw = rowBounds.width;
        int ch = rowBounds.height;

        if (!rendererComponent.isVisible()){
            ch = 0;
        }
        rendererPane.paintComponent(g, rendererComponent, list, cx, cy, cw, ch, true);

    }
}