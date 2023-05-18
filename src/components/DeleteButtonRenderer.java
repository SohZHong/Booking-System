package components;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class DeleteButtonRenderer implements TableCellRenderer {
    
    private final JButton button;
    
    public DeleteButtonRenderer() {
        
        button = new JButton();
        button.setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
        
        button.setText("Delete");

        //Button select animation
        button.setForeground(new Color(215,76,76));
        button.setBackground(new Color(215,76,76));

        return button;
    }
}
