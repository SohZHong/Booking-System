package panels;

import components.LoginForm;
import components.LogoDisplay;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class LoginPanel extends JPanel {
    
    private final GridLayout layout;
    private final LoginForm loginForm;
    private final LogoDisplay logoDisplay;
    
    public LoginPanel(){
        
        //Initializing variables and objects
        loginForm = new LoginForm();
        logoDisplay = new LogoDisplay(450, 450);
        layout = new GridLayout(1, 2);
        
        this.setLayout(layout);
        
        //Adding Components
        this.add(logoDisplay);
        this.add(loginForm);
    }
}
