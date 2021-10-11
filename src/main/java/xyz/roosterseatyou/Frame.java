package xyz.roosterseatyou;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.util.Objects;
import java.util.Random;

public class Frame extends JFrame implements ActionListener {
    JButton button = new JButton();
    JTextField websiteName = new JTextField();
    JTextField charBox = new JTextField();
    JTextPane passBox = new JTextPane();
    JTextPane listBox = new JTextPane();
    JButton githubLink = new JButton();
    Frame(){


        button.setBounds(100, 0, 200, 50);
        websiteName.setBounds(50, 50, 300, 20);
        charBox.setBounds(100, 80, 200, 20 );
        passBox.setBounds(50, 110, 300, 20);
        listBox.setBounds(400, 0, 550, 400);
        githubLink.setBounds(860, 600, 200, 50);


        button.addActionListener(this);
        githubLink.addActionListener(this);

        button.setText("Generate Password!");
        button.setBackground(Color.CYAN);
        button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));

        charBox.setToolTipText("Character Amount");

        passBox.setBackground(Color.RED);
        passBox.setEditable(false);

        listBox.setEditable(false);

        githubLink.setText("Go to GitHub Repository");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1080,1920);
        this.setBackground(Color.MAGENTA);
        this.setVisible(true);

        this.add(button);
        this.add(websiteName);
        this.add(charBox);
        this.add(passBox);
        this.add(listBox);
        this.add(githubLink);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button) {
            Random rand = new Random();
            String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789!@#$%^&*=-_+|,<.>;:'~][{}()";
            StringBuilder pass = new StringBuilder();

            String charAmtStr = charBox.getText();
            int charAmtInt;
            //Gets int from string and maybe adds an Easter egg????
            try {
                charAmtInt = Integer.parseInt(charAmtStr);
            } catch (NumberFormatException err) {
                //Rickroll
                if(Objects.equals(charBox.getText(), "rickroll")){
                    try {
                        Desktop.getDesktop().browse(URI.create("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                //Error box
                }else {
                    passBox.setBackground(Color.RED);
                    passBox.setText("Not a valid Character Amount!");
                }
                return;
            }
            //Checks if character amount < 8 and fixes it
            if (charAmtInt < 8) {
                charBox.setText("8");
                charAmtInt = 8;
            }
            //Checks if character amount is > 50 and fixes it
            if (charAmtInt > 50) {
                charBox.setText("50");
                charAmtInt = 50;
            }

            for (int i = 0; i < charAmtInt; i++) {
                char rChar = characters.charAt(rand.nextInt(characters.length()));
                pass.append(rChar);
            }

            button.setText("Generate New Password!");
            passBox.setText(pass.toString());
            passBox.setBackground(Color.GREEN);

            //List maker
            String webName = websiteName.getText();
            String listBoxStr = listBox.getText();

            if(listBoxStr == null) {
                listBox.setText(webName + ": " + pass);
            }else {
                listBox.setText(listBoxStr + "\n" + webName + ": " + pass);
            }

        }else
            //GitHub button
        if(e.getSource() == githubLink){
            try {
                Desktop.getDesktop().browse(URI.create("https://github.com/Roosterseatyou/PasswordGenerator"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
