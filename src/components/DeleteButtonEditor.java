package components;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class DeleteButtonEditor extends DefaultCellEditor{
    private final JButton button;
    
    public DeleteButtonEditor(){
        super(new JCheckBox());
        button = new JButton();
        
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        
        button.setText("Delete");
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to delete this row?", "Delete row confirmation", 
                    JOptionPane.YES_NO_OPTION);

                if (confirmed == JOptionPane.YES_OPTION) {
                    // Call the delete method in your table model to remove the row
                    ((BookingTableModel)table.getModel()).deleteRow(row);
                }
            }
        });
        
        return button;
    }
}

