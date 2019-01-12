//import java.awt.*;



import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.Writer;
import java.util.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;


public class CreateDictionary extends JFrame {
    JTextField textField;
    JTextArea textArea;
    JButton translateButton;
    JButton addButton;
    JLabel labelEng;
    JLabel labelBg;
    JFrame frame;
    JPanel panel;
    Icon icon1;
    Icon icon2;
     boolean flag;

    public CreateDictionary() {
        drawAndshowGui();
        translator();


    }

    public void drawAndshowGui() {
        frame = new JFrame("Eng-BG Dictionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        textField = new JTextField(30);
        textField.setBounds(150, 350, 500, 300);

        translateButton = new JButton("Преведи ");
        //translateButton.setFont(new Font("Calibri", Font.BOLD, 17));
        translateButton.setBounds(340, 400, 200, 400);

        addButton = new JButton("Добави ");


        UIManager.put("Button.font", "Calibri");
        //addButton.setFont(new Font("Calibri", Font.BOLD, 17));
        addButton.setBounds(100, 400, 200, 400);

        textArea = new JTextArea();
        textArea.setRows(2);
        textArea.setBounds(340, 300, 200, 300);
        //textArea.setFont(new Font("Serif", Font.ITALIC, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        icon1 = new ImageIcon("src/eng_flag.png");
        labelEng = new JLabel(icon1);
        labelEng.setLocation(300, 200);
        icon2 = new ImageIcon("src/flag_bg.png");
        labelBg = new JLabel(icon2);
        labelBg.setLocation(300, 200);

        panel.add(textField);

        panel.add(translateButton);
        panel.add(addButton);

        panel.add(textArea);


        frame.getContentPane().add(panel);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

   // public void addLanguageoption() {
       // if (option(text1)) {
           // panel.add(labelBg);
           // panel.add(labelEng);
        //}else{
           // panel.add(labelEng);
            //panel.add(labelBg);
        //}
    //}

    public void translator()  {

        translateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText().trim();
                try {

                    HashMap<String, String> map = generateDict();
                    Set<Map.Entry<String, String>> set = map.entrySet();

                    if ((text != null) && (!text.isEmpty())) {
                        char text1 = text.charAt(0);

                      //  if (option(text1)){
                           // addLanguageoption();
                        //}

                        if (!option(text1) && !map.containsKey(text) ){
                            JOptionPane.showMessageDialog(frame, "" + "The word is missing Please click button add");
                            addWords(map);
                        }
                        for (String s : text.split(" ")) {
                            for (Map.Entry<String, String> me : set) {

                                if (s.equals(me.getKey())) {
                                    text = me.getValue() + " ";
                                } else if (s.equals(me.getValue())) {
                                    text = me.getKey() + " ";
                                }


                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "" + "Моля въведете дума");
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                textArea.append(text);
                textField.selectAll();
                textArea.setCaretPosition(textArea.getDocument().getLength());
            }
        });
    }

    public void addWords(HashMap<String, String>map) {
        Set<Map.Entry<String, String>> set = map.entrySet();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText().trim();
                String text2 = textArea.getText().trim();


                map.put(text, text2);

                System.out.println(Arrays.asList(map));
                saveTofile();
            }
            public void saveTofile(){

                Writer writer = null;
                try {
                    writer = new BufferedWriter(new OutputStreamWriter(
                            new FileOutputStream(new File("src/test.txt"))));
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                Writer finalWriter = writer;
                map.forEach((key, value) -> {
                    try {
                        finalWriter.write(key + " " + value + System.lineSeparator());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });
                try {
                    writer.flush();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    writer.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }


            }
        });
    }

    boolean isCyrillic(char text1) {

        return Character.UnicodeBlock.CYRILLIC.equals(Character.UnicodeBlock.of(text1));
    }
    public boolean option(char text1){
        if (isCyrillic(text1)) {
            return true;


        } else {
            return false;
        }
    }

    static HashMap<String, String> generateDict() throws Exception {
        File file = new File("src/test.txt");

        HashMap<String, String> map = new HashMap<String, String>();

        String line;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ", 2);
            if (parts.length >= 2) {
                String key = parts[0];
                String value = parts[1];
                map.put(key, value);
            }
            //reader.close();
        }
        return map;
    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                new CreateDictionary();
            }
        });
    }
}