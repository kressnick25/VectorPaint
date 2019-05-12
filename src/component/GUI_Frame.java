package component;

//This is work in progress, needs to be moved into seperate files,
//this can be the frame of the GUI


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GUI_Frame extends JFrame implements ActionListener, Runnable {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;

    private JPanel pnlOne;
    private JPanel pnlTwo;
    private JPanel pnlBtn;
    private JPanel pnlFou;
    private JPanel pnlDisplay;

    private JButton Load;
    private JButton Unload;
    private JButton Find;
    private JButton Switch;
    private JButton ImageButton;

    private JTextArea display;

    public GUI_Frame(String title) throws HeadlessException {
        super(title);

    }

    private JPanel createPanel(Color c) {
        //Create a JPanel object and store it in a local var
        //set the background colour to that passed in c
        //Return the JPanel object
        JPanel newPnl = new JPanel();
        newPnl.setBackground(c);
        return newPnl;
    }

    private JButton createButton(String str) {
        JButton newBtn = new JButton();
        newBtn.setText(str);
        newBtn.addActionListener(this);
        return newBtn;
    }
    private JButton JButtonImage(String str){
        JButton newBtn = new JButton(new ImageIcon(getClass().getClassLoader()
                .getResource("component/Line.png")));
        newBtn.setText("");
        newBtn.addActionListener(this);
        return newBtn;

    }

    private void layoutButtonPanel() {
        GridBagLayout layout = new GridBagLayout();
        pnlBtn.setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 100;
        constraints.weighty = 100;

        //addToPanel(pnlBtn, Load, constraints, 0, 0, 2, 1);
        //addToPanel(pnlBtn, Unload, constraints, 3, 0, 2, 1);
        //addToPanel(pnlBtn, Find, constraints, 0, 2, 2, 1);
        //addToPanel(pnlBtn, Switch, constraints, 3, 2, 2, 1);
        addToPanel(pnlBtn, ImageButton, constraints, 0, 2, 3, 1);
    }

    private void addToPanel(JPanel jp, Component c, GridBagConstraints
            constraints, int x, int y, int w, int h) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        jp.add(c, constraints);
    }

//    private JTextArea areDisplay() {
//        display = new JTextArea();
//        display.setEditable(false);
//        display.setLineWrap(true);
//        display.setFont(new Font("Arial", Font.BOLD, 24));
//        display.setBorder(BorderFactory.createEtchedBorder());
//
//        return display;
//    }
    //dsfsdfsjdfgvasdfjasasjdfhj

    private void createGUI() {

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        pnlOne = createPanel(Color.GRAY);
        pnlTwo = createPanel(Color.GRAY);
        pnlBtn = createPanel(Color.GRAY);
        pnlFou = createPanel(Color.GRAY);
        pnlDisplay = createPanel(Color.WHITE);

        //Load = createButton("Load");
        //Find = createButton("Find");
        //Switch = createButton("Switch");
        //Unload = createButton("Unload");
        ImageButton = JButtonImage("ImageButton");

        layoutButtonPanel();

        getContentPane().add(pnlOne, BorderLayout.EAST);
        getContentPane().add(pnlTwo, BorderLayout.WEST);
        getContentPane().add(pnlBtn, BorderLayout.SOUTH);
        getContentPane().add(pnlFou, BorderLayout.NORTH);
        getContentPane().add(pnlDisplay, BorderLayout.CENTER);

        display = new JTextArea();
        display.setEditable(false);
        display.setLineWrap(true);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setBorder(BorderFactory.createEtchedBorder());

        add(display, BorderLayout.CENTER);

//        areDisplay();

        repaint();
        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Get event source
        Object src = e.getSource();
        //Consider the alternatives - not all active at once.
        if (src == Load) {
            JButton btn = ((JButton) src);
            display.setText(btn.getText().trim());
        }

    }

    @Override
    public void run() {
        createGUI();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new GUI_Frame("BorderLayout"));
//        PanelsAndWidgets gui = new PanelsAndWidgets("bull");

    }
}