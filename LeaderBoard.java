import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class LeaderBoard extends JFrame implements DEFAULTS{
    private static JPanel Components;
    
    LeaderBoard(User user){
        Components = new JPanel();
        setLayout(new BorderLayout(10,10));

        Components.setLayout(new BoxLayout(Components, BoxLayout.Y_AXIS));

        // Add components to panel before adding to frame
        Components.add(Title_Text(), BorderLayout.NORTH);
        Components.add(Box.createVerticalStrut(5)); // Spacing for between components
        Components.add(Get_Leaderboards(user), BorderLayout.CENTER);
        Components.add(Box.createVerticalStrut(5)); // Spacing for between components
        Components.add(Back_Button(), BorderLayout.SOUTH);

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
        Title.setVerticalAlignment(JLabel.TOP);

        return Title;
    }

    private JPanel Get_Leaderboards(User user){
        // Panel for leaderboard (both recent and best games contained in panel)
        JPanel BestGamesPanel = new JPanel();
        BestGamesPanel.setLayout(new BoxLayout(BestGamesPanel, BoxLayout.Y_AXIS));    // Have text fields stacked on top of eachother
        JLabel TitleBest = new JLabel("BEST GAMES OF ALL TIME");
        TitleBest.setFont(new Font(TEXT_FONT, Font.BOLD, 20));
        TitleBest.setAlignmentX(Component.CENTER_ALIGNMENT);
        BestGamesPanel.add(TitleBest);

        // Add respective games now
        BestGamesPanel.add(DisplayGames(user.GetBestGames()));

        JLabel TitleRecent = new JLabel("MOST RECENT GAMES");
        TitleRecent.setFont(new Font(TEXT_FONT, Font.BOLD, 20));
        TitleRecent.setAlignmentX(Component.CENTER_ALIGNMENT);
        BestGamesPanel.add(TitleRecent);

        // Add respective games now
        BestGamesPanel.add(DisplayGames(user.GetRecentGames()));

        return BestGamesPanel;
    }

    private JList<String> DisplayGames(ArrayList<GameStats> Users_Games){
        DefaultListModel<String> Model = new DefaultListModel<>();
        JList<String> list = new JList<>(Model);
        String add;
        // Add row for labels for each "leaderboard"
        add = "";
        add += String.format("%-15s", "Score");
        add += String.format("%12s", "Difficulty");
        add += String.format("%25s", "Time to Beat");
        add += String.format("%30s", "Date Achieved");
        Model.addElement(add);

        // Add every best/recent game to JList to be displayed
        for(GameStats g : Users_Games){
            add = "";
            add += String.format("%-15s", g.getScore());
            add += String.format("%12s", g.getDifficulty());
            add += String.format("%25s", g.getTimeToBeat());
            add += String.format("%30s", g.getDate());
            Model.addElement(add);
        }
        return list;
    }
    
    ActionListener GoBack = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e){
            setVisible(false);
            dispose();
        }
    };
}
