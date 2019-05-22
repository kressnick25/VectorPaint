import AdvancedShape.AdvancedShape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;
//import java.awt.event.KeyEvent;
import java.awt.event.*;



enum ShapeType {
    Rectangle,
    Ellipse,
    Line,
    Polygon,
    Plot,
}

public class GUI_Frame extends JFrame implements ActionListener, Runnable, KeyListener {
    private Timer timer=new Timer(5, this);
    private static final int WIDTH = 1250;
    private static final int HEIGHT = 1000;
    private int keyCode;
    private JPanel pnlBtn;
    private GraphicsCanvas display;
    private JMenu file, edit, help;
    private JButton LineButton, RectangleButton, EllipseButton,
                    PolygonButton, FillButton, PenButton;
    private JMenuItem   cut, copy, paste, selectAll,
                        fileOpen, fileSave, fileSaveAs,
                        fileNew, helpBtn, undo;


    // mouse movement
    private MouseListener mouseDraw = new MouseListener();
    private Color initialcolor = Color.RED;

    public GUI_Frame(String title) throws HeadlessException {
        super(title);
        timer.start();
    }

    private JPanel createPanel(Color c) {
        JPanel newPnl = new JPanel();
        newPnl.setBackground(c);
        return newPnl;
    }


    private JButton JButtonImageInitializer(JButton newBtn) {
        newBtn.setText("");
        newBtn.addActionListener(this);
        return newBtn;
    }


    private JButton JButtonImage(String imagePath) {
        //TODO resize all images
        try {
            JButton newBtn = new JButton(new ImageIcon(imagePath));
            newBtn = JButtonImageInitializer(newBtn);
            newBtn.setPreferredSize(new Dimension(70, 60));

            return newBtn;
        } catch (Exception e) {

            return null;
        }
    }
    public void keyPressed(KeyEvent e){
        int keyCodeNew = e.getKeyCode();
        if(keyCodeNew == KeyEvent.VK_S){
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            int returnValue = jfc.showSaveDialog(this);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();

                ArrayList<AdvancedShape> shapes = this.display.getShapes();
                Vec vec = new Vec(selectedFile.getAbsolutePath(), shapes);
                vec.save();
            }
        }
        if(keyCodeNew == KeyEvent.VK_Z){
            Shape latest = display.getLatest();
            while (display.getLatest() == latest){
                display.clearLast();
            }
        }
        if(keyCodeNew == KeyEvent.VK_H){
            JOptionPane.showMessageDialog(pnlBtn,
                    "Basic KeyBinds: H for Help, S for Save, Z for Undo",
                    "Help",
                    JOptionPane.INFORMATION_MESSAGE);
        }



        }
    public void keyReleased(KeyEvent e){
        //System.out.println("dfd");

    }
    public void keyTyped(KeyEvent e){
        //System.out.println("dfd");

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
        addToPanel(pnlBtn, EllipseButton, constraints, 0, 2, 2, 1);
        addToPanel(pnlBtn, PolygonButton, constraints, 0, 3, 2, 1);
        addToPanel(pnlBtn, FillButton, constraints, 0, 4, 2, 1);
        addToPanel(pnlBtn, PenButton, constraints, 0, 5, 2, 1);
    }

    private void addToPanel(JPanel jp, Component c,
                            GridBagConstraints constraints,
                            int x, int y, int w, int h)
    {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        jp.add(c, constraints);
    }


    private void createTopMenu() {
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        undo = new JMenuItem("Undo");
        fileNew = new JMenuItem("New");
        fileOpen = new JMenuItem("Open");
        fileSave = new JMenuItem("Save");
        fileSaveAs = new JMenuItem("Save As");
        selectAll = new JMenuItem("Select All");
        helpBtn = new JMenuItem("Help");

        helpBtn.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        undo.addActionListener(this);
        selectAll.addActionListener(this);
        fileNew.addActionListener(this);
        fileOpen.addActionListener(this);
        fileSave.addActionListener(this);
        fileSaveAs.addActionListener(this);

        JMenuBar mb = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        help = new JMenu("Help");
        file.add(fileOpen);
        file.add(fileSave);
        file.add(fileSaveAs);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(undo);
        help.add(helpBtn);
        mb.add(file);
        mb.add(edit);
        mb.add(help);

        add(mb);
        setJMenuBar(mb);
        setVisible(true);
    }

    private void createGUI() {
        String imgPath = "./img/";
        setSize(WIDTH, HEIGHT);
        //setPreferredSize(new Dimension(1000, 3000));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        pnlBtn = createPanel(Color.GRAY);
        JPanel pnlDisplay = createPanel(Color.WHITE);

        LineButton = JButtonImage(imgPath + "buttons/line.png");
        RectangleButton = JButtonImage(imgPath + "buttons/rectangle.png");
        EllipseButton = JButtonImage(imgPath + "buttons/ellipse.png");
        PolygonButton = JButtonImage(imgPath + "buttons/polygon.png");
        FillButton = JButtonImage(imgPath + "buttons/fill.png");
        PenButton = JButtonImage(imgPath + "buttons/pen.png");

        layoutButtonPanel();

        getContentPane().add(pnlBtn, BorderLayout.WEST);
        getContentPane().add(pnlDisplay, BorderLayout.CENTER);
        display = new GraphicsCanvas();

        createTopMenu();

        // add mouse listener
        mouseDraw.setCanvas(display);
        display.addMouseListener(mouseDraw);
        display.addMouseMotionListener(mouseDraw);


        display.setBorder(BorderFactory.createEtchedBorder());
        display.setBounds(5, 5, 360, 320);
        add(display, BorderLayout.CENTER);

//        areDisplay();


        addKeyListener(this);

        setFocusable(true);
        requestFocus();
        repaint();
        setVisible(true);
    }

    // TODO mouse event here using current shape, add to GraphicsCanvas

    @Override
    public void actionPerformed(ActionEvent e) {
        //Get event source
        Object src = e.getSource();

        //Consider the alternatives - not all active at once.
        if (src == LineButton) {
            mouseDraw.setType(ShapeType.Line);
            setFocusable(true);
            requestFocus();

        }
        if (src == RectangleButton) {
            mouseDraw.setType(ShapeType.Rectangle);
            System.out.println("d");
            setFocusable(true);
            requestFocus();


        }
        if (src == EllipseButton) {
            mouseDraw.setType(ShapeType.Ellipse);
            setFocusable(true);
            requestFocus();
        }
        if (src == PolygonButton) {
            mouseDraw.setType(ShapeType.Polygon);
            setFocusable(true);
            requestFocus();
        }
        if (src == FillButton) {
            Color ColorFill = JColorChooser.showDialog(this, "Select a color", initialcolor);
            mouseDraw.setFillColor(ColorFill);
            setFocusable(true);
            requestFocus();


        }
        if (src == PenButton) {
            Color colorPen = JColorChooser.showDialog(this, "Select a color", initialcolor);
            mouseDraw.setPenColor(colorPen);
            setFocusable(true);
            requestFocus();


        }
        // MENU ITEMS
        if (src == helpBtn) {
            JOptionPane.showMessageDialog(pnlBtn,
                    "Basic KeyBinds: H for Help, S for Save, Z for Undo",
                    "Help",
                    JOptionPane.INFORMATION_MESSAGE);


        }

        if (src == fileSave || src == fileSaveAs) {
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            int returnValue = jfc.showSaveDialog(this);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();

                ArrayList<AdvancedShape> shapes = this.display.getShapes();
                Vec vec = new Vec(selectedFile.getAbsolutePath(), shapes);
                vec.save();
            }
        }

        if (src == fileOpen) {
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            int returnValue = jfc.showOpenDialog(this);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                this.display.clear();
                File selectedFile = jfc.getSelectedFile();
                System.out.println(selectedFile.getAbsolutePath());
                Vec vec = new Vec(selectedFile.getAbsolutePath());
                vec.read();
                display.load(vec.get());
                this.display.repaint();
            }
        }

        if (src == undo){
            Shape latest = display.getLatest();
            while (display.getLatest() == latest){
                display.clearLast();
            }
            //TODO fix error when no shapes in GUI, crashes

        }

        // WINDOW REFRESH
        if (e.getSource()==timer){
            repaint(); // repait every timer expiry
        }

    }

    @Override
    public void run() {
        createGUI();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
        SwingUtilities.invokeLater(new GUI_Frame("BorderLayout"));

    }
}



// Java program to implement JColorChooser
// class using ActionListener
//import java.awt.event.*;
//        import java.awt.*;
//        import javax.swing.*;
//
//public class ColorChooserExample extends
//        JFrame implements ActionListener {
//
//    // create a button
//    JButton b = new JButton("color");
//
//    Container c = getContentPane();
//
//    // Constructor
//    ColorChooserExample()
//    {
//
//        // set Layout
//        c.setLayout(new FlowLayout());
//
//        // add Listener
//        b.addActionListener(this);
//
//        // add button to the Container
//        c.add(b);
//    }
//
//    public void actionPerformed(ActionEvent e)
//    {
//
//        Color initialcolor = Color.RED;
//
//        // color chooser Dialog Box
//        Color color = JColorChooser.showDialog(this,
//                "Select a color", initialcolor);
//
//        // set Background color of the Conatiner
//        c.setBackground(color);
//    }
//
//    // Main Method
//    public static void main(String[] args)
//    {
//
//        ColorChooserExample ch = new ColorChooserExample();
//        ch.setSize(400, 400);
//        ch.setVisible(true);
//        ch.setDefaultCloseOperation(EXIT_ON_CLOSE);
//    }
//}

