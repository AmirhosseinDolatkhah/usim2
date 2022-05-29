package ahd.usim2;

import ahd.ulib.swingutils.MainFrame;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new MainFrame() {{
            add(new JButton("Hi"), BorderLayout.NORTH);
        }});
    }
}
