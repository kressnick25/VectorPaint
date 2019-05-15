package component;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GUI_Frame extends JFrame implements ActionListener, Runnable {

    public static final int WIDTH = 1250;
    public static final int HEIGHT = 1000;


    private JPanel pnlBtn;
    private JPanel pnlDisplay;

    private JButton LineButton;
    private JButton RectangleButton;
    private JButton EclipseButton;
    private JButton PolygonButton;
    private JButton FillButton;
    private JButton PenButton;



    private JTextArea display;
    private JMenuBar mb;
    private JMenu file,edit,help;
    private JMenuItem cut,copy,paste,selectAll;


    public GUI_Frame(String title) throws HeadlessException {
        super(title);

    }

    private JPanel createPanel(Color c) {

        JPanel newPnl = new JPanel();
        newPnl.setBackground(c);
        return newPnl;
    }


    private JButton JButtonImageInitializer(JButton newBtn){
        newBtn.setText("");
        newBtn.addActionListener(this);
        return newBtn;
    }

    private JButton JButtonImage(String str){
        //TODO resize all images

        JButton newBtn;
        if (str == "LineButton") {
            newBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("component/GUIButtons/Line.png")));
            newBtn = JButtonImageInitializer(newBtn);
            newBtn.setPreferredSize(new Dimension(70, 60));

            return newBtn;
        }
        if (str == "RectangleButton") {
            newBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("component/GUIButtons/Rectangle.png")));
            newBtn = JButtonImageInitializer(newBtn);
            newBtn.setPreferredSize(new Dimension(70, 60));


            return newBtn;
        }
        if (str == "EclipseButton") {
            newBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("component/GUIButtons/Eclipse.png")));
            newBtn = JButtonImageInitializer(newBtn);
            newBtn.setPreferredSize(new Dimension(70, 60));


            return newBtn;
        }
        if (str == "PolygonButton") {
            newBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("component/GUIButtons/Polygon.png")));
            newBtn = JButtonImageInitializer(newBtn);
            newBtn.setPreferredSize(new Dimension(70, 60));
            return newBtn;
        }
        if (str == "FillButton") {
            newBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("component/GUIButtons/Fill.png")));
            newBtn = JButtonImageInitializer(newBtn);
            newBtn.setPreferredSize(new Dimension(70, 60));


            return newBtn;
        }
        if (str == "PenButton") {
            newBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("component/GUIButtons/Pen.png")));
            newBtn = JButtonImageInitializer(newBtn);
            newBtn.setPreferredSize(new Dimension(70, 60));


            return newBtn;
        }


        return null;


    }

    private void layoutButtonPanel() {
        GridBagLayout layout = new GridBagLayout();
        pnlBtn.setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 100;
        constraints.weighty = 1;


        addToPanel(pnlBtn, LineButton, constraints, 0, 0, 2, 1);
        addToPanel(pnlBtn, RectangleButton, constraints, 0, 1, 2, 1);
        addToPanel(pnlBtn, EclipseButton, constraints, 0, 2, 2, 1);
        addToPanel(pnlBtn, PolygonButton, constraints, 0, 3, 2, 1);
        addToPanel(pnlBtn, FillButton, constraints, 0, 4, 2, 1);
        addToPanel(pnlBtn, PenButton, constraints, 0, 5, 2, 1);


    }

    private void addToPanel(JPanel jp, Component c, GridBagConstraints
            constraints, int x, int y, int w, int h) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        jp.add(c, constraints);
    }


    private void createTopMenu(){



        cut=new JMenuItem("cut");
        copy=new JMenuItem("copy");
        paste=new JMenuItem("paste");
        selectAll=new JMenuItem("selectAll");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        mb=new JMenuBar();
        file=new JMenu("File");
        edit=new JMenu("Edit");
        help=new JMenu("Help");
        edit.add(cut);edit.add(copy);edit.add(paste);edit.add(selectAll);
        mb.add(file);mb.add(edit);mb.add(help);
        add(mb);
        setJMenuBar(mb);
        setVisible(true);
        }

    private void createGUI() {
        setSize(WIDTH, HEIGHT);
        //setPreferredSize(new Dimension(1000, 3000));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        pnlBtn = createPanel(Color.GRAY);

        pnlDisplay = createPanel(Color.WHITE);


        LineButton = JButtonImage("LineButton");
        RectangleButton = JButtonImage("RectangleButton");
        EclipseButton = JButtonImage("EclipseButton");
        PolygonButton = JButtonImage("PolygonButton");
        FillButton = JButtonImage("FillButton");
        PenButton = JButtonImage("PenButton");



        layoutButtonPanel();


        getContentPane().add(pnlBtn, BorderLayout.WEST);
        getContentPane().add(pnlDisplay, BorderLayout.CENTER);
        display = new JTextArea();

        createTopMenu();

        display.setEditable(true);
        display.setLineWrap(true);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setBorder(BorderFactory.createEtchedBorder());
        display.setBounds(5,5,360,320);
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
        if (src == LineButton) {
            //TODO Do stuff

        }
        if (src == RectangleButton) {
            //TODO Do stuff

        }
        if (src == EclipseButton) {
            //TODO Do stuff

        }
        if (src == PolygonButton) {
            //TODO Do stuff


        }
        if (src == FillButton) {
            //TODO Do stuff


        }
        if (src == PenButton) {
            //TODO Do stuff


        }
        if(src==cut)
            display.cut();
        if(src==paste)
            display.paste();
        if(src==copy)
            display.copy();
        if(src==selectAll)
            display.selectAll();


}

    @Override
    public void run() {
        createGUI();

    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new GUI_Frame("BorderLayout"));

    }
}
