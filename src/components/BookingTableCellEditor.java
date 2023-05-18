package components;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.InputVerifier;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

public class BookingTableCellEditor extends AbstractCellEditor implements TableCellEditor{
    private TableCellEditor editor;
    private final InputVerifier verifier;
    private final JTextField cellField;
    private Object originalValue;
    
    public BookingTableCellEditor(InputVerifier verifier){
        this.verifier = verifier;
        this.cellField = new JTextField();
        cellField.setInputVerifier(verifier);
    }
    
    @Override
    public Object getCellEditorValue() {
        return cellField.getText();
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        
        originalValue = value; //Update value of text field after validation successful
        cellField.setText((String) value);
        editor = new DefaultCellEditor(cellField);
        
        return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
    }
    
    @Override
    public boolean stopCellEditing(){
        if (!verifier.verify(cellField)){
            JOptionPane.showMessageDialog(cellField, "Invalid Input");
            cellField.setText((String) originalValue); //Restore original value if failed validation
            return false;
        }
        
        return super.stopCellEditing();
    }
}