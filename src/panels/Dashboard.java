package panels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import managers.FileManager;
import managers.PanelManager;
import static managers.PanelManager.*;
import utils.DateTime;

public class Dashboard extends JPanel{
    
    private final GroupLayout layout = new GroupLayout(this);
    
    private final JLabel title;
    private final JLabel date;
    private final JButton viewBooking;
    private final JButton viewRooms;
    private final JButton modifyLogin;
    private final JButton logout;

    
    public Dashboard() throws InterruptedException{
        
        
        viewBooking = new JButton("View Bookings");
        viewBooking.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                 PanelManager.showPanel(MANAGE_PANEL);
            }
        });
        
        viewRooms = new JButton("View Rooms");
        viewRooms.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelManager.showPanel(LISTINGS_PANEL);
            }
        });
        
        modifyLogin = new JButton("Modify Login");
        modifyLogin.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            try {
                FileManager fileManager = new FileManager("staff");
                String usernameInput = JOptionPane.showInputDialog(null, "Enter New Username: ");
                String passwordInput = JOptionPane.showInputDialog(null,"Enter New Password: ");
                
                if (!(usernameInput == null || passwordInput == null)){
                    String[] data  = {usernameInput, passwordInput};
                    fileManager.writeFile(data, false);
                }

            } 
            catch (IOException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        });
        
        logout = new JButton("Logout");
        logout.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelManager.showPanel(LOGIN_PANEL);
            }
        });
        
        title = new JLabel("Dashboard");
        title.setFont(new Font("STXihei", Font.BOLD, 30));
        
        date = new JLabel(DateTime.getLocalDate());
        date.setFont(new Font("STXihei", Font.PLAIN, 20));

        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                    
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(viewRooms, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(viewBooking, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(modifyLogin, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(logout, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
                    )
                        
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                
                                
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(title)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(date)
                            )
                        )
                        .addGap(62, 62, 62)
                    )
                )
            )
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                    
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(title, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                    .addComponent(date)
                )
                .addGap(200, 200, 200)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewRooms, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewBooking, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyLogin, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                    .addComponent(logout, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                )
                    
                .addContainerGap(270, Short.MAX_VALUE)
            )
        );
        
    }

}
