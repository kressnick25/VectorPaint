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
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.*;


/*
ShapeType enum used to differentiate between shapes
 */
enum ShapeType {
    Rectangle,
    Ellipse,
    Line,
    Polygon,
    Plot,
}

public class GUI_Frame extends JFrame implements ActionListener, Runnable, KeyListener {
    // Screen Refresh Timer
    private Timer timer=new Timer(5, this);
    // Initial screen dimensions
    private static int prevScreenHeight = 1000;
    private static int prevScreenWidth = 1000;
    private static int numWindows = 0;
    // Window components
    private GraphicsCanvas display;
    private JPanel pnlBtn, historyPanel;
    private JMenu file, edit, help, grid;
    private JButton LineButton, RectangleButton, EllipseButton,
            PolygonButton, FillButton, PenButton, PlotButton, undoButton;
    private JComboBox undoHistoryComboBox;
    private JMenuItem   cut, copy, paste, selectAll,
            fileOpen, fileSave, fileSaveAs,
            fileNew, helpBtn, undo, gridBtn;
    // Other
    private ArrayList<AdvancedShape> shapes = new ArrayList<>();
    private ArrayList<Integer> pressed = new ArrayList<>();
    private MouseListener mouseDraw = new MouseListener();

    /**
     * Construct new empty window
     * @param title
     * @throws HeadlessException
     */
    public GUI_Frame(String title) throws HeadlessException {
        super(title);
        timer.start();
    }

    /**
     * Construct new window, load components from file.
     * @param title
     * @param jfc a JFileChooser object to select file
     * @throws HeadlessException
     */
    public GUI_Frame(String title, JFileChooser jfc) throws HeadlessException {
        super(title);
        timer.start();
        //gets selected file path
        File selectedFile = jfc.getSelectedFile();
        //inputs file location and into vec
        Vec vec = new Vec(selectedFile.getAbsolutePath());
        try {
            vec.read();
        } catch (Exception ex){
            JOptionPane.showMessageDialog(pnlBtn,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        //gets list of shapes
        this.shapes = vec.get();
        System.out.println(vec.get());
    }


    public synchronized void keyPressed(KeyEvent e) {
        //Get pressed keyCode
        int keyCodeNew = e.getKeyCode();

        if(keyCodeNew == 17){
            pressed.add(17);
        }
        //Check is pressed key is "s"
        if(keyCodeNew == KeyEvent.VK_S){
            if(!pressed.isEmpty()) {

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
        }
        if(keyCodeNew == KeyEvent.VK_O){
            if(!pressed.isEmpty()) {

                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                //Open file chooser
                int returnValue = jfc.showOpenDialog(this);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    numWindows++;
                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    } catch (Exception ignored) {
                    }
                    GUI_Frame gui = new GUI_Frame("Paint - Assignment2", jfc);
                    SwingUtilities.invokeLater(gui);
                }
            }
        }
        if(keyCodeNew == KeyEvent.VK_Z){
            if(!pressed.isEmpty()){
                Shape latest = display.getLatest();
                if (latest != null) {
                    while (display.getLatest() == latest) {
                        display.clearLast();
                    }
                }
            }

        }
        //Help KeyBind
        if(keyCodeNew == KeyEvent.VK_H){
            if(!pressed.isEmpty()) {
                JOptionPane.showMessageDialog(pnlBtn,
                        "Basic KeyBinds: H for Help, S for Save, Z for Undo",
                        "Help",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }
    // Required methods
    public void keyReleased(KeyEvent e){
        if(!pressed.isEmpty()) {
            pressed.remove(0);
        }

    }
    public void keyTyped(KeyEvent e){

    }
    // Setup Panels in window
    private void createLayoutHistoryTopPanel(){
        //History Panel
        historyPanel = new JPanel();
        historyPanel.setBackground(new Color(0xBFE3FF));
        undoButton = new JButton(pnlBtn,"Undo", "undo.png", new Dimension(60, 50));
        undoButton.addActionListener(this);
        historyPanel.setLayout(new GridBagLayout());
        undoHistoryComboBox = new JComboBox();
        historyPanel.setComponentOrientation(
                ComponentOrientation.LEFT_TO_RIGHT);
        historyPanel.add(undoButton);
        historyPanel.add(undoHistoryComboBox);
        undoHistoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                String selectedItem = (String)cb.getSelectedItem();
                String[] split = selectedItem.split(" ");
                int historyIndex = Integer.parseInt(split[0]);
                // remove all items in array up to history index
                display.trimToIndex(historyIndex);
            }
        });
    }
    private void createLayoutButtonPanel() {
        //create side button panel
        pnlBtn = new JPanel();
        pnlBtn.setBackground(new Color(0xBFE3FF));
        String imgPath = "./img/";
        //initializes all buttons
        Dimension size = new Dimension(100, 60);
        PlotButton = new JButton(pnlBtn,"Plot","Plot.jpg", size);
        PlotButton.addActionListener(this);
        LineButton = new JButton(pnlBtn, "Line","line.png", size);
        LineButton.addActionListener(this);
        RectangleButton = new JButton(pnlBtn,"Box","rectangle.png", size);
        RectangleButton.addActionListener(this);
        EllipseButton = new JButton(pnlBtn,"Ellipse","ellipse.png", size);
        EllipseButton.addActionListener(this);
        PolygonButton = new JButton(pnlBtn,"Polygon", "polygon.png", size);
        PolygonButton.addActionListener(this);
        FillButton = new JButton(pnlBtn,"Fill Colour", "fill.png", size);
        FillButton.addActionListener(this);
        PenButton = new JButton(pnlBtn,"Pen Colour", "pen.png", size);
        PenButton.addActionListener(this);
        //Layout settings for side buttons
        pnlBtn.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 100;
        constraints.weighty = 1;
        //addShape buttons to panels
        addToPanel(pnlBtn, PlotButton, constraints, 0, 0, 2,1);
        addToPanel(pnlBtn, LineButton, constraints, 0, 1, 2, 1);
        addToPanel(pnlBtn, RectangleButton, constraints, 0, 2, 2, 1);
        addToPanel(pnlBtn, EllipseButton, constraints, 0, 3, 2, 1);
        addToPanel(pnlBtn, PolygonButton, constraints, 0, 4, 2, 1);
        addToPanel(pnlBtn, FillButton, constraints, 0, 5, 2, 1);
        addToPanel(pnlBtn, PenButton, constraints, 0, 6, 2, 1);
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
        gridBtn = new JMenuItem("Grid");
        helpBtn = new JMenuItem("help");

        // add action listeners to MenuBar Buttons
        gridBtn.addActionListener(this);
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

        // Setup menu bar
        JMenuBar mb = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        help = new JMenu("Help");
        grid = new JMenu("Grid");

        file.add(fileOpen);
        file.add(fileSave);
        file.add(fileNew);
        file.add(fileSaveAs);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(undo);

        grid.add(gridBtn);
        help.add(helpBtn);

        mb.add(file);
        mb.add(edit);
        mb.add(help);
        mb.add(grid);

        //addShape to GUI and set visible
        add(mb);
        setJMenuBar(mb);
        setVisible(true);
    }

    // Add a component to a JPanel
    private void addToPanel(JPanel jp, Component c, GridBagConstraints constraints, int x, int y, int w, int h) {
        //adding buttons to panel
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        jp.add(c, constraints);
    }

    private void createGUI() {
        //set size of GUI
        setSize(prevScreenWidth, prevScreenHeight);
        //setPreferredSize(new Dimension(1000, 3000));
        //close operation on exit button click
        setLayout(new BorderLayout());
        //create side button panel
        //Drawing canvas creation
        JPanel pnlDisplay = new JPanel();
        pnlDisplay.setBackground(Color.WHITE);

        createLayoutButtonPanel();
        createLayoutHistoryTopPanel();

        getContentPane().add(pnlBtn, BorderLayout.WEST);
        getContentPane().add(historyPanel, BorderLayout.NORTH);
        getContentPane().add(pnlDisplay, BorderLayout.CENTER);
        display = new GraphicsCanvas(undoHistoryComboBox);
        createTopMenu();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(numWindows < 1) {
                    System.out.println("WindowClosingDemo.windowClosing");
                    System.exit(0);
                }else{
                    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    System.out.println("Number of active windows: " + numWindows);
                    numWindows--;
                }

            }
        });

        // addShape mouse listener
        mouseDraw.setCanvas(display);
        display.addMouseListener(mouseDraw);
        display.addMouseMotionListener(mouseDraw);

        display.setBorder(BorderFactory.createEtchedBorder());
        display.setBounds(5, 5, 360, 320);
        add(display, BorderLayout.CENTER);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        //addShape keyboard listeners and focus
        addKeyListener(this);
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Dimension size = display.getSize();

                if(size.width != size.height){
                    // equalize width and height
                    if(size.width > size.height){
                        display.setSize(size.height, size.height);
                    }
                    else{
                        display.setSize(size.width, size.width);
                    }
                    // force display min size
                    if (size.width < 200 || size.height < 200){
                        display.setSize(200, 200);
                    }
                }

                display.updateScale(
                        new Dimension(prevScreenWidth, prevScreenHeight),
                        display.getSize() );
                prevScreenWidth = display.getSize().width;
                prevScreenHeight = display.getSize().height;
            }
        });
        if(!shapes.isEmpty()){
            display.setShapes(shapes);

        }

        setFocusable(true);
        requestFocus();
        repaint();
        setVisible(true);

    }

    //sets the type of shape the user wants to use
    private void setAction(ShapeType type){
        mouseDraw.setType(type);
        setFocusable(true);
        requestFocus();
    }

    private void setFillColor(boolean setFill){
        //sets the color
        Color newColor = JColorChooser.showDialog(this, "Select a color", Color.RED);
        if (newColor == null) {
            if (setFill){
                newColor = new Color(255, 255, 255);
            } else {
                newColor = new Color(0);
            }
        }
        if (setFill){
            mouseDraw.setFillColor(newColor);
            FillButton.setBackground(newColor);
            FillButton.setContentAreaFilled(false);
            FillButton.setOpaque(true);
        } else {
            mouseDraw.setPenColor(newColor);
            PenButton.setBackground(newColor);
            PenButton.setContentAreaFilled(false);
            PenButton.setOpaque(true);
        }

        setFocusable(true);
        requestFocus();
    }

    private void saveFile(){
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

    private void openFile(){
        //opening new file
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        //Open file chooser
        int returnValue = jfc.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
              numWindows++;
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            GUI_Frame gui = new GUI_Frame("Paint - Assignment2", jfc);
            SwingUtilities.invokeLater(gui);
        }
    }
    private void undo(){
        Shape latest = display.getLatest();
        if (latest != null) {
            while (display.getLatest() == latest) {
                display.clearLast();
            }
        }
    }
    private void helpMessage(){
        //Dialog help Message
        JOptionPane.showMessageDialog(pnlBtn,
                "Basic KeyBinds: H for Help, S for Save, Z for Undo",
                "Help",
                JOptionPane.INFORMATION_MESSAGE);
    }
    private void gridInput(){
        String errorMessage = "";
        do {
            // Show input dialog with current error message, if any
            String stringInput = JOptionPane.showInputDialog(errorMessage + "Enter number between 0 and 1. 0 for no Grid");
            try {
                Double number = Double.parseDouble(stringInput);
                if (number > 1 || number < 0) {
                    errorMessage = "That number is not within the \n" + "allowed range!\n";
                } else {
                    JOptionPane
                            .showMessageDialog(null, "The number you chose is " + number + ".");
                    errorMessage = ""; // no more error
                    mouseDraw.setInterval(number);
                }
            } catch (NumberFormatException err) {
                // The typed text was not an integer
                errorMessage = "The text you typed is not a number.\n";
            }
        } while (!errorMessage.isEmpty());
    }

    private void newFile(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
        SwingUtilities.invokeLater(new GUI_Frame("Paint - Assignment"));
        numWindows++;
        System.out.println(numWindows);
    }

    private void refreshWindow(){
        repaint(); // repaint every timer expiry\
        // check display still square
        int width = display.getSize().width;
        int height = display.getSize().height;
        if (height != width){
            // set to lowest if not
            if (height > width) display.setSize(width, width);
            else display.setSize(height, height);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Get event source
        Object src = e.getSource();
        //side button check
        if (src == PlotButton) setAction(ShapeType.Plot);
        else if (src == LineButton) setAction(ShapeType.Line);
        else if (src == RectangleButton) setAction(ShapeType.Rectangle);
        else if (src == EllipseButton) setAction(ShapeType.Ellipse);
        else if (src == PolygonButton) setAction(ShapeType.Polygon);
        else if (src == FillButton) setFillColor(true);
        else if (src == PenButton) setFillColor(false);
        // MENU ITEMS
        else if (src == gridBtn) gridInput();
        else if (src == helpBtn) helpMessage();
        else if (src == fileSave || src == fileSaveAs) saveFile();
        else if (src == fileOpen) openFile();
        else if (src == undo) undo();
        else if (src == undoButton) undo();
        else if (src == fileNew) newFile();
        // WINDOW REFRESH
        if (e.getSource()==timer) refreshWindow();
    }

    @Override
    public void run() {
        createGUI();
    }
}