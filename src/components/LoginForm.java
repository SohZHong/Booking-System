package components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.PREFERRED_SIZE;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import managers.FileManager;
import managers.PanelManager;

public class LoginForm extends JPanel {

    //initializing components
    private final JPanel mainPanel, formPanel;
    private final GroupLayout formLayout;
    private final GridBagLayout gridBagLayout;
    private final JTextField userField;
    private final JPasswordField passField;
    private final JButton submitButton;
    private final JLabel companyName;
    private final JLabel welcomeText;
    private final JLabel userLabel;
    private final JLabel passLabel;

    
    public LoginForm(){
        
        //Creating objects
        mainPanel = new JPanel();
        formPanel = new JPanel();
        formLayout = new GroupLayout(formPanel);
        gridBagLayout = new GridBagLayout();
        companyName = new JLabel();
        welcomeText = new JLabel();
        userLabel = new JLabel();
        passLabel = new JLabel();
        userField = new JTextField(25); //No Input verifiers
        passField = new JPasswordField(25);
        submitButton = new JButton("Login");
        
        //Setting initial layout
        this.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(gridBagLayout);
        mainPanel.add(formPanel);
        add(mainPanel, BorderLayout.CENTER);
        
        
        //Setting label text
        companyName.setText("APRooms");
        welcomeText.setText("Welcome Back!");
        userLabel.setText("Username");
        passLabel.setText("Password");
        
        //Set to call the requestFocus method of the input fields when the mnemonic is activated.
        userLabel.setLabelFor(userField);
        passLabel.setLabelFor(passField);
        
        //Customizing company logo
        companyName.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 50));
        companyName.setForeground(new Color(102, 102, 102));
        
        //Authenticate user upon button click
        submitButton.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {

            FileManager fileManager = new FileManager("staff");

            //Getting values of text field
            String userInput = userField.getText();

            String passInput = String.valueOf(passField.getPassword()); //getPassword returns char Array so convert it to String

            try {
                //Retrieving login credentials
                fileManager.readFile();
            } catch (IOException ex) {
                Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
            }
                String[] userCredentials = fileManager.readLine(0);


            if (userCredentials[0].equals(userInput) && (userCredentials[1].equals(passInput))) {

                //Switch to hotel page after verified
                PanelManager.showPanel(PanelManager.DASHBOARD_PANEL);

            }
            else {
                    JOptionPane.showMessageDialog(formPanel, "Login Unsuccessful, Please try again");
            }
        }
        });
        
        formPanel.setLayout(formLayout);
        formLayout.setAutoCreateGaps(true);
        formLayout.setAutoCreateContainerGaps(true);
        formLayout.setHorizontalGroup(
            formLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(companyName)
                )
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(welcomeText)
                )
                .addGroup(formLayout.createParallelGroup()
                    .addComponent(userField, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                    .addComponent(userLabel)
                )
                .addGroup(formLayout.createParallelGroup()
                    .addComponent(passField, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                    .addComponent(passLabel)
                )
                .addGroup(formLayout.createSequentialGroup()
                    .addComponent(submitButton, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                )
            );
        
        
        formLayout.setVerticalGroup(
            formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addGroup(formLayout.createSequentialGroup()
                    .addComponent(companyName)
                        .addGap(20, 30, 35)
                        
                    .addComponent(welcomeText)
                        .addGap(100, 100, 100)
                    .addGroup(formLayout.createSequentialGroup()
                        .addComponent(userField, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                        .addComponent(userLabel)
                    )
                    .addGap(15, 15, 15)
                    .addGroup(formLayout.createSequentialGroup()
                        .addComponent(passField, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                        .addComponent(passLabel)
                    )
                    
                    .addGap(30, 35, 40)
                    .addComponent(submitButton, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                )
            );
    }
}
