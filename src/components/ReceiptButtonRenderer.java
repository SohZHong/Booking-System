package components;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


public class ReceiptButtonRenderer implements TableCellRenderer {
    
    private JButton button;
    
    public ReceiptButtonRenderer() {
        
        button = new JButton();
        button.setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
        
        button.setText("Receipt");

        //Button select animation
        button.setForeground(new Color(41,134,204));
        button.setBackground(new Color(41,134,204));

        return button;
    }
}