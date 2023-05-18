package utils;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class EmailInputVerifier extends InputVerifier{
    
    @Override
    public boolean verify(JComponent input) {
        //Get the input of textfield
        String email = ((JTextField) input).getText();
        
        //Regex Pattern for Email
        Pattern pattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
        Matcher matcher = pattern.matcher(email);
        
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
            JOptionPane.showMessageDialog(input, "Invalid Email");
            return false;
        }
    }
}