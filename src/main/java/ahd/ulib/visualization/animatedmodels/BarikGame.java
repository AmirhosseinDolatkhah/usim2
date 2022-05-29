package ahd.ulib.visualization.animatedmodels;

import ahd.ulib.swingutils.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class BarikGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new MainFrame() {{
                   add(new JPanel() {
                       private final List<Point> points = new ArrayList<>();

                       @Override
                       public void paintComponent(Graphics g) {
                           super.paintComponent(g);

                           var g2d = (Graphics2D) g;
                           g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                           var radius = 600;
                           g2d.setColor(Color.GRAY);
                           g2d.fillOval((getWidth() - radius) / 2, (getHeight() - radius) / 2, radius, radius);

                           g2d.setColor(Color.RED);

                           var pieceRadius = 20;
                           g2d.fillOval((getWidth() - pieceRadius) / 2,
                                   (getHeight() - pieceRadius) / 2, pieceRadius, pieceRadius);

                           points.forEach(p -> g2d.fillOval((p.x - pieceRadius) / 2,
                                   (p.y - pieceRadius) / 2, pieceRadius, pieceRadius));
                       }

                       {
                           addMouseListener(new MouseAdapter() {
                               @Override
                               public void mouseClicked(MouseEvent e) {
                                   points.add(e.getPoint());
                                   System.out.println(e.getPoint());
                                   repaint();
                               }
                           });
                       }
                   });
               }
           }
        );
    }
}
