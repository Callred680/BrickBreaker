/*
 *
 * Displays menu for brick breaker game
 * 
 */
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Menu extends JFrame implements DEFAULTS {
    private static JPanel Components;
    private User user;
    private BrickBreaker BB;
    private static LeaderBoard LB;

    Menu(){
        Components = new JPanel();
        
        // Set up layouts for panel and frame
        setLayout(new GridBagLayout());
        Components.setLayout(new BoxLayout(Components, BoxLayout.Y_AXIS));

        // Add components to panel before adding to frame
        Components.add(Title_Text());
        Components.add(Box.createVerticalStrut(5)); // Spacing for between components
        Components.add(Play_Button());
        Components.add(Box.createVerticalStrut(5)); // Spacing for between components
        Components.add(User_Button());
        Components.add(Box.createVerticalStrut(5)); // Spacing for between components
        Components.add(Leaderboard_Button());

        add(Components);

        // Set values for frame
        setTitle("Main Menu");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(MENU_WIDTH, MENU_HEIGHT));
        pack();
    }

    public User getUser(){
        return this.user;
    }

    private JButton Play_Button(){
        JButton Play = new JButton("Start Game");
        Play.addActionListener(PlayGame);
        Play.setMaximumSize(new Dimension(MENU_WIDTH/2, Play.getMinimumSize().height));
        Play.setAlignmentX(Component.CENTER_ALIGNMENT);
        return Play;
    }
    private JButton User_Button(){
        JButton User = new JButton("User Profile");
        User.addActionListener(UserInfo);
        User.setMaximumSize(new Dimension(MENU_WIDTH/2, User.getMinimumSize().height));
        User.setAlignmentX(Component.CENTER_ALIGNMENT);

        return User;
    }
    private JButton Leaderboard_Button(){
        JButton Scores = new JButton("Leaderboards");
        Scores.addActionListener(SeeScores);
        Scores.setMaximumSize(new Dimension(MENU_WIDTH/2, Scores.getMinimumSize().height));
        Scores.setAlignmentX(Component.CENTER_ALIGNMENT);

        return Scores;
    }
    private JLabel Title_Text(){
        JLabel Title = new JLabel("BRICK BREAKER");
        Title.setFont(new Font(TEXT_FONT, Font.BOLD, 45));
        Title.setAlignmentX(Component.CENTER_ALIGNMENT);

        return Title;
    }
   

    ActionListener PlayGame = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e){
            if(BB != null){
                // Remove old frame if user decides to play again
                BB.setVisible(false);
                BB.dispose();
            }
            // Display dialog box for user to select difficulty level
            int UserChoice = JOptionPane.showOptionDialog(null, "Please select which difficulty to play on:", "Difficulty",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            // -1 is returned if user exits out of the dialog box instead of selecting displayed option
            if(UserChoice != -1){
                Menu.this.setVisible(false);
                BB = new BrickBreaker(Difficulty.valueOf(options[UserChoice]));
                BB.setVisible(true);                
            }
        }
    };

    ActionListener UserInfo = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e){
            user = new User();  // Create new user
            /*
             * TODO: Allow user log in first
             */
        }
    };
    ActionListener SeeScores = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e){
            // Check if user has been assigned first before trying to display leaderboards
            if(user == null){
                JOptionPane.showMessageDialog(null, "Error! No user information to be displayed. Please sign in first", "User missing", JOptionPane.WARNING_MESSAGE, null);
            }else{
                if(LB != null){
                    // Remove old frame if user decides to play again
                    LB.setVisible(false);
                    LB.dispose();
                }
                // Display dialog box for user to select difficulty level
                LB = new LeaderBoard(user); // Send specific user to obtain specific leaderboard stats
                LB.setVisible(true);            
            }
        }
    };
}
