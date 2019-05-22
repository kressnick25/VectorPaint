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


/*
ShapeType enum used to differiatate between shapes
 */
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
            PolygonButton, FillButton, PenButton, PlotButton;
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
        //Creates new Jpanel
        JPanel newPnl = new JPanel();
        //Sets background of the JPanel
        newPnl.setBackground(c);
        return newPnl;
    }


    private JButton JButtonImageInitializer(JButton newBtn) {
        //Adds ActionListener to button
        newBtn.addActionListener(this);
        return newBtn;
    }


    private JButton JButtonImage(String imagePath) {
        try {
            //creates new button
            JButton newBtn = new JButton(new ImageIcon(imagePath));
            //initializes buttons and sets them to newBtn
            newBtn = JButtonImageInitializer(newBtn);
            //sets size of button
            newBtn.setPreferredSize(new Dimension(70, 60));

            return newBtn;
            //catches Error
        } catch (Exception e) {
            //Dialog box showing Error
            JOptionPane.showMessageDialog(pnlBtn,
                    "Error in Saving to FIle: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    public void keyPressed(KeyEvent e){
        //Get pressed keyCode
        int keyCodeNew = e.getKeyCode();
        //Check is pressed key is "s"
        if(keyCodeNew == KeyEvent.VK_S){
            //Open File chooser
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int returnValue = jfc.showSaveDialog(this);
            //check file chooser return value
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                //get selected file
                File selectedFile = jfc.getSelectedFile();
                //create array of shapes currently displayed on screen
                ArrayList<AdvancedShape> shapes = this.display.getShapes();
                //create new instance of vec, inputting file and shapes array
                Vec vec = new Vec(selectedFile.getAbsolutePath(), shapes);
                //save to file
                vec.save();
            }
        }
        if(keyCodeNew == KeyEvent.VK_O){
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            //Open file chooser
            int returnValue = jfc.showOpenDialog(this);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                //clears current display
                this.display.clear();
                //gets selected file path
                File selectedFile = jfc.getSelectedFile();
                System.out.println(selectedFile.getAbsolutePath());
                //inputs file location and into vec
                Vec vec = new Vec(selectedFile.getAbsolutePath());
                vec.read();
                //gets list of shapes
                display.load(vec.get());
                //repaints display with selected shapes
                this.display.repaint();
            }
        }
        if(keyCodeNew == KeyEvent.VK_Z){
            //TODO does this just work?

            Shape latest = display.getLatest();
            if (latest != null) {
                while (display.getLatest() == latest) {
                    display.clearLast();
                }
            }
        }
        if(keyCodeNew == KeyEvent.VK_H){
            //Help KeyBind
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
        //Layout settings for side buttons
        GridBagLayout layout = new GridBagLayout();
        pnlBtn.setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 100;
        constraints.weighty = 1;
        //add buttons to panels
        addToPanel(pnlBtn, PlotButton, constraints, 0, 0, 2,1);
        addToPanel(pnlBtn, LineButton, constraints, 0, 1, 2, 1);
        addToPanel(pnlBtn, RectangleButton, constraints, 0, 2, 2, 1);
        addToPanel(pnlBtn, EllipseButton, constraints, 0, 3, 2, 1);
        addToPanel(pnlBtn, PolygonButton, constraints, 0, 4, 2, 1);
        addToPanel(pnlBtn, FillButton, constraints, 0, 5, 2, 1);
        addToPanel(pnlBtn, PenButton, constraints, 0, 6, 2, 1);
    }

    private void addToPanel(JPanel jp, Component c,
                            GridBagConstraints constraints,
                            int x, int y, int w, int h)
    {
        //adding buttons to panel
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        jp.add(c, constraints);
    }


    private void createTopMenu() {
        //top navigation bar buttons
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
        //adding action listeners to MenuBar Buttons
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
        //add these buttons to
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(undo);
        help.add(helpBtn);
        mb.add(file);
        mb.add(edit);
        mb.add(help);
        //add to GUI and set visible
        add(mb);
        setJMenuBar(mb);
        setVisible(true);
    }

    private void createGUI() {
        String imgPath = "./img/";
        //set size of GUI
        setSize(WIDTH, HEIGHT);
        //setPreferredSize(new Dimension(1000, 3000));
        //close operation on exit button click
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        //create side button panel
        pnlBtn = createPanel(Color.GRAY);
        //Drawing canvas creation
        JPanel pnlDisplay = createPanel(Color.WHITE);
        //initializes all buttons
        PlotButton = JButtonImage(imgPath + "buttons/plot.png");
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

        //add keyboard listeners and focus
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
        //side button check
        if (src == PlotButton) {
            //sets the type of shape the user wants to use
            mouseDraw.setType(ShapeType.Plot);
            setFocusable(true);
            requestFocus();
        }
        else if (src == LineButton) {
            mouseDraw.setType(ShapeType.Line);
            setFocusable(true);
            requestFocus();

        }
        else if (src == RectangleButton) {
            mouseDraw.setType(ShapeType.Rectangle);
            setFocusable(true);
            requestFocus();
        }
        else if (src == EllipseButton) {
            mouseDraw.setType(ShapeType.Ellipse);
            setFocusable(true);
            requestFocus();
        }
        else if (src == PolygonButton) {
            mouseDraw.setType(ShapeType.Polygon);
            setFocusable(true);
            requestFocus();
        }
        else if (src == FillButton) {
            //sets the color
            Color ColorFill = JColorChooser.showDialog(this, "Select a color", initialcolor);
            mouseDraw.setFillColor(ColorFill);
            setFocusable(true);
            requestFocus();


        }
        else if (src == PenButton) {
            Color colorPen = JColorChooser.showDialog(this, "Select a color", initialcolor);
            mouseDraw.setPenColor(colorPen);
            setFocusable(true);
            requestFocus();


        }
        // MENU ITEMS
        else if (src == helpBtn) {
            //Dialog help Message
            JOptionPane.showMessageDialog(pnlBtn,
                    "Basic KeyBinds: H for Help, S for Save, Z for Undo",
                    "Help",
                    JOptionPane.INFORMATION_MESSAGE);


        }
        else if (src == fileSave || src == fileSaveAs) {
            //save button
            //opens file chooser
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            int returnValue = jfc.showSaveDialog(this);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                //inputs created shapes and file to vec class and saves
                ArrayList<AdvancedShape> shapes = this.display.getShapes();
                Vec vec = new Vec(selectedFile.getAbsolutePath(), shapes);
                vec.save();
            }
        }

        else if (src == fileOpen) {
            //opening new file
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            //Open file chooser
            int returnValue = jfc.showOpenDialog(this);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                //clears current display
                this.display.clear();
                //gets selected file path
                File selectedFile = jfc.getSelectedFile();
                System.out.println(selectedFile.getAbsolutePath());
                //inputs file location and into vec
                Vec vec = new Vec(selectedFile.getAbsolutePath());
                vec.read();
                //gets list of shapes
                display.load(vec.get());
                //repaints display with selected shapes
                this.display.repaint();
            }
        }
        else if (src == undo) {
            //
            Shape latest = display.getLatest();
            if (latest != null) {
                while (display.getLatest() == latest) {
                    display.clearLast();
                }
            }
        }

        // WINDOW REFRESH
        if (e.getSource()==timer){
            repaint(); // repaint every timer expiry
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