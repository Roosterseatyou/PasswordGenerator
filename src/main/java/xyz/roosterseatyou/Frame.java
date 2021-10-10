package xyz.roosterseatyou;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Frame extends JFrame implements ActionListener {
    JButton button = new JButton();
    JTextField charBox = new JTextField();
    JTextPane passBox = new JTextPane();
    JPopupMenu popup = new JPopupMenu();
    Frame(){


        button.setBounds(400, 100, 200, 50);
        passBox.setBounds(350, 200, 300, 20);
        charBox.setBounds(400, 160, 200, 20 );
        button.addActionListener(this);
        button.setText("Generate Password!");
        button.setBackground(Color.CYAN);
        button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        passBox.setBackground(Color.RED);
        charBox.setToolTipText("Character Amount");
        passBox.setEditable(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1000,1000);
        this.setVisible(true);
        this.setBackground(Color.MAGENTA);
        this.add(button);
        this.add(passBox);
        this.add(charBox);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Random rand = new Random();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789!@#$%^&*=-_+|,<.>;:'~][{}()";
        StringBuilder pass = new StringBuilder();
        String charAmtStr = charBox.getText();
        int charAmtInt = 0;
        try {
            charAmtInt = Integer.parseInt(charAmtStr);
        } catch (NumberFormatException err) {
            passBox.setBackground(Color.RED);
            passBox.setText("Not a valid Character Amount!");
            return;
        }
        if(charAmtInt < 8){
            passBox.setText("Must be more than 8 Characters for your own sake!");
            charBox.setText("8");
            charAmtInt = 8;
        }
        if(charAmtInt > 50){
            passBox.setText("You do not need a password with >50 Characters!");
            charBox.setText("50");
            charAmtInt = 50;
        }
        for (int i = 0; i < charAmtInt; i++) {
            char rChar = characters.charAt(rand.nextInt(characters.length()));
            pass.append(rChar);
        }
        if (e.getSource() == button) {
            button.setText("Generate New Password!");
            passBox.setText(pass.toString());
            passBox.setBackground(Color.GREEN);
        }
    }

    public static boolean containsLetters(String input) {
        if (input == null) {
            return false;
        }
        int length = input.length();
        for(int i = 0; i < length; i++){
            if(Character.isLetter(input.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
