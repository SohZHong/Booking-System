package components;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.PREFERRED_SIZE;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import managers.PanelManager;
import static managers.PanelManager.BOOKING_FORM;
import utils.LoadImage;

public class RoomListingsItem extends JPanel {
    
    private BookingForm bookingForm;
    
    private final JLabel roomImage;
    private final JLabel roomLabel;
    private final JLabel rateLabel;
    private final JLabel roomTypeLabel;
    private final JLabel wifiLabel;
    private final JLabel roomSizeLabel;
    private final JLabel smokingLabel;
    private final JButton bookingButton;
    
    private final String roomID;
    private final String roomType;
    private final String bedType;
    private final String wifi;
    private final String smoking;
    private final String rate;
            
    public RoomListingsItem(Room room) throws ParseException{
        roomImage = new JLabel();
        roomLabel = new JLabel();
        bookingButton = new JButton();
        rateLabel = new JLabel();
        roomTypeLabel = new JLabel();
        wifiLabel = new JLabel();
        roomSizeLabel = new JLabel();
        smokingLabel = new JLabel();

        roomID = room.getRoomID();
        roomType = room.getRoomType();
        bedType = room.getBedType();
        wifi = String.valueOf(room.getWifi());
        smoking = String.valueOf(room.getSmoking());
        rate = Double.toString(Room.calculateRoomCharges(room));
        
        roomImage.setIcon(new ImageIcon(LoadImage.getScaledImage(("Room " + roomID + ".png"), 200, 200)));

        roomLabel.setFont(new java.awt.Font("STXihei", 0, 24));
        roomLabel.setText("Room " + roomID);

        bookingButton.setText("Book Now");
        bookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookingForm = PanelManager.getBookingForm();
                bookingForm.setBookingRoomID(Integer.parseInt(roomID));
                try {
                    bookingForm.loadForm();
                } catch (ParseException ex) {
                    Logger.getLogger(RoomListingsItem.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(RoomListingsItem.class.getName()).log(Level.SEVERE, null, ex);
                }
                PanelManager.showPanel(BOOKING_FORM);
            }
        });

        rateLabel.setFont(new Font("STXihei", 0, 36));
        rateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rateLabel.setText(rate);

        roomTypeLabel.setText("Room Type: " + roomType);

        wifiLabel.setText("Wifi: " + (wifi.substring(0, 1).toUpperCase() + wifi.substring(1)));

        roomSizeLabel.setText("Bed Size: " + bedType);

        smokingLabel.setText("Smoking: " + (smoking.substring(0, 1).toUpperCase() + smoking.substring(1)));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
                    
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roomImage, PREFERRED_SIZE, 200, PREFERRED_SIZE)
                .addGap(18, 18, 18)
                    
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(roomLabel)
                        
                    .addGroup(layout.createSequentialGroup()
                            
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                            .addComponent(roomTypeLabel)
                            .addComponent(wifiLabel)
                        )
                        .addGap(50, 50, 50)
                            
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                            .addComponent(smokingLabel)
                            .addComponent(roomSizeLabel)
                        )
                    )
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 350, Short.MAX_VALUE)
                    
                .addGroup(layout.createParallelGroup(Alignment.LEADING, false)
                    .addComponent(bookingButton, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rateLabel, DEFAULT_SIZE, 149, Short.MAX_VALUE)
                )
                .addContainerGap()
            )
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(roomImage, PREFERRED_SIZE, 200, PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                            
                        .addGroup(layout.createParallelGroup(Alignment.LEADING, false)
                            .addComponent(rateLabel, PREFERRED_SIZE, 84, PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(roomLabel, PREFERRED_SIZE, 46, PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                    .addComponent(roomTypeLabel)
                                    .addComponent(roomSizeLabel)
                                )
                            )
                        )
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                .addComponent(wifiLabel)
                                .addComponent(smokingLabel)
                            )
                            .addComponent(bookingButton, PREFERRED_SIZE, 40, PREFERRED_SIZE)
                        )
                    )
                )
                .addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE)
            )
        );
    }
    
}
