package components;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class ReceiptButtonEditor extends DefaultCellEditor{
 
    private JButton button;
    
    public ReceiptButtonEditor(){
        super(new JCheckBox());
        button = new JButton();
        
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        
        button.setText("Receipt");
        
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Call the view method from within the model
                ((BookingTableModel)table.getModel()).viewReceipt(row);
            }
        });
        
        return button;
    }
    
}