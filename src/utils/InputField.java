package utils;

import javax.swing.JTextField;

//Factory code to generate textfields with input verifiers
public class InputField {
    
    public static JTextField createTextField(int columns){
        
        JTextField textField = new JTextField(columns);
        textField.setInputVerifier(new TextInputVerifier());
        
        return textField;
    }

    public static JTextField createContactField(int columns){
        
        JTextField contactField = new JTextField(columns);
        
        contactField.setInputVerifier(new ContactInputVerifier());
        
        return contactField;
    }
    
    public static JTextField createICField(int columns){
        
        JTextField icField = new JTextField(columns);
        
        icField.setInputVerifier(new ICInputVerifier());
        
        return icField;
    }
    
    public static JTextField createEmailField(int columns){
       
        
        JTextField emailField = new JTextField(columns);
        
        emailField.setInputVerifier(new EmailInputVerifier());
        
        return emailField;
    }
}