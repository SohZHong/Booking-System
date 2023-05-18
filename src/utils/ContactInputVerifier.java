package utils;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ContactInputVerifier extends InputVerifier{

    @Override
    public boolean verify(JComponent input) {
        //Get the input of textfield
        String contact = ((JTextField) input).getText();
        
        //Regex Pattern for Malaysian Phone Numbers
        //8 digits for 011* and 7 digits for 012*
        Pattern pattern = Pattern.compile("^(01[12])[0-9]{7,8}$");
        Matcher matcher = pattern.matcher(contact);
        
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
            JOptionPane.showMessageDialog(input, "Invalid Contact Number");
            return false;
        }
    }
}
