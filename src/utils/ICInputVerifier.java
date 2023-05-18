package utils;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ICInputVerifier extends InputVerifier{
    @Override
    public boolean verify(JComponent input) {
        //Get the input of textfield
        String ic = ((JTextField) input).getText();
        
        //Regex Pattern for Email
        Pattern pattern = Pattern.compile("\\d{12}"); //12 digits
        Matcher matcher = pattern.matcher(ic);
        
        boolean isValid = matcher.matches();
        
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
            JOptionPane.showMessageDialog(input, "Invalid IC");
            return false;
        }
    }
}