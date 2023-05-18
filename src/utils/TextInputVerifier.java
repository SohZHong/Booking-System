package utils;

import java.awt.Color;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TextInputVerifier extends InputVerifier{
    @Override
    public boolean verify(JComponent input) {
        //Get the input of textfield
        String text = ((JTextField) input).getText();
        
        boolean isValid = !text.isBlank(); //Check if empty or contains white spaces only
        
        if (isValid) {
            input.setForeground(new Color(51, 0, 0));
        }
        else {
           input.setForeground(Color.RED);
        }
        
        return isValid;
    }
    
    //Alert the staff upon invalid input
    @Override
    public boolean shouldYieldFocus(JComponent input){
        boolean isValid = verify(input);
        
        if (isValid){
            return true;
        }
        else {
            JOptionPane.showMessageDialog(input, "This field can't be empty");
            return false;
        }
    }
}