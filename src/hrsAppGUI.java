import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class hrsAppGUI extends JFrame {
    private JPanel mainPanel;
    private JTextField msrpField;
    private JTextField mapField;
    private JButton calcButton;
    private JLabel msrpLabel;
    private JLabel mapLabel;
    private JLabel resultLabel;
    private JLabel instructionLabel1;
    private JLabel instructionLabel2;
    private JLabel creditsLabel;
    private JButton copyButton;
    private JButton clearButton;

    public hrsAppGUI(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        setSize(500, 300);
        setLocation(700, 300);

        calcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Objects.equals(msrpField.getText(), "") && !Objects.equals(mapField.getText(), "")) {
                    double msrp = Double.parseDouble(msrpField.getText());
                    double map = Double.parseDouble(mapField.getText());
                    double save = msrp - map;
                    double roundVal = Math.round(save * 100.00) / 100.00;
                    double percent = 100 - map * 100 / msrp;
                    resultLabel.setText("Save equals to " + roundVal + "$ which is " + Math.round(percent) + "%");
                } else if (Objects.equals(msrpField.getText(), "") && !Objects.equals(mapField.getText(), "")) {
                    double map1x = Double.parseDouble(mapField.getText());
                    double map1xRound = Math.round(map1x * 100.00) / 100.00;
                    double map3x = map1x * 3 - map1x * 3 * 0.05;
                    double map3xRound = Math.round(map3x * 100.00) / 100.00;
                    double map3x95 = Math.ceil(map3xRound) - 0.05;
                    double map6x = map3x95 * 2 - 1;
                    double map6xRound = Math.round(map6x * 100.00) / 100.00;
                    double map6x95 = Math.ceil(map6xRound) - 0.05;
                    resultLabel.setText("1xMAP= " + map1xRound + " 3xMAP= " + map3x95 + " 6xMAP= " + map6x95);
                } else resultLabel.setText("Please fill in both fields for MSRP and MAP or at least MAP.");
            }
        });

        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String copyData = resultLabel.getText();
                StringSelection ss = new StringSelection(copyData);
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                msrpField.setText("");
                mapField.setText("");
                resultLabel.setText("Please enter MSRP and MAP and click calculate");
            }
        });

        msrpField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    mapField.requestFocus();
                }
            }
        });

        mapField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    //calcButton.requestFocus();
                    calcButton.doClick();
                }
            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new hrsAppGUI("My HRS Calculations");
        frame.setVisible(true);
    }
}
