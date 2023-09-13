import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JList;

public class LeaderBoard extends JFrame implements DEFAULTS{
    private static JPanel Components;
    
    LeaderBoard(){
        Components = new JPanel();
        setLayout(new GridBagLayout());
        
        Components.setLayout(new BoxLayout(Components, BoxLayout.Y_AXIS));

        // Add components to panel before adding to frame
        Components.add(Title_Text());
        Components.add(Box.createVerticalStrut(5)); // Spacing for between components
        Components.add(Get_Leaderboard());
        Components.add(Box.createVerticalStrut(5)); // Spacing for between components
        Components.add(Back_Button());

        add(Components);
        // Set values for frame
        setTitle("Leaderboards");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(LEADERBOARD_WIDTH, LEADERBOARD_HEIGHT));
        pack();
    }

    private JButton Back_Button(){
        JButton Return = new JButton("Return to Menu");
        Return.addActionListener(GoBack);
        Return.setMaximumSize(new Dimension(LEADERBOARD_WIDTH/2, Return.getMinimumSize().height));
        Return.setAlignmentX(Component.CENTER_ALIGNMENT);
        return Return;
    }

    private JLabel Title_Text(){
        JLabel Title = new JLabel("LEADERBOARDS");
        Title.setFont(new Font(TEXT_FONT, Font.BOLD, 45));
        Title.setAlignmentX(Component.CENTER_ALIGNMENT);

        return Title;
    }

    private JList<String> Get_Leaderboard(){

        return new JList<>();
    }
    
    ActionListener GoBack = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e){
            setVisible(false);
            dispose();
        }
    };
}
