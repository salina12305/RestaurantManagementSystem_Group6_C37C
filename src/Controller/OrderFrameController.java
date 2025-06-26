
package Controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class OrderFrameController {
    
        public static void applyHoverEffect(JButton button) {
        Color originalColor = button.getBackground();
        Color hoverColor = new Color(200, 200, 255); 

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }

            @Override
          public void mouseExited(MouseEvent e) {
                button.setBackground(originalColor);
            }
        });
    }
    
}
