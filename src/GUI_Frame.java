import AdvancedShape.AdvancedShape;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.*;


    /**
    * ShapeType enum used to differiatate between shapes
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
    private static int WIDTH = 1000;
    private static int HEIGHT = 1000;
    private int prevScreenHeight = 1000;
    private int prevSreenWidth = 1000;
    private JPanel pnlBtn, historyPanel;
    private GraphicsCanvas display;
    private JMenu file, edit, help, grid;
    private JButton LineButton, RectangleButton, EllipseButton,
            PolygonButton, FillButton, PenButton, PlotButton, undoButton;
    private JComboBox undoHistoryComboBox;
    private JMenuItem fileOpen, fileSave, fileSaveAs,
            fileNew, helpBtn, undo, gridBtn;
    private boolean focus = true;
    private static int numWindows = 0;
    private ArrayList<AdvancedShape> shapes = new ArrayList<>();

    private ArrayList<Integer> pressed = new ArrayList<>();

    // mouse movement
    private MouseListener mouseDraw = new MouseListener();
    private Color initialcolor = Color.RED;

    /**
     * Constructs a new GUI_Frame
     * @param title Title to appear at top of window
     * @throws HeadlessException
     */
    public GUI_Frame(String title) throws HeadlessException {
        super(title);
        timer.start();
    }

    /**
     * Constructs a new GUI_Frame
     * @param title Title to appear at top of window
     * @param shapes An array of shapes to pre-draw to canvas
     * @throws HeadlessException
     */
    public GUI_Frame(String title, ArrayList shapes) throws HeadlessException {
        super(title);
        timer.start();
        this.shapes = shapes;
        setFocusable(true);
        requestFocus();

    }

    /**
     * Create a new panel with a color
     * @param c a new Color
     * @return
     */
    private JPanel createPanel(Color c) {  //Creates new Jpanel
        JPanel newPnl = new JPanel();
        //Sets background of the JPanel
        newPnl.setBackground(c);
        return newPnl;
    }

    /**
     * Create a new JButton with an Image and Text
     * @param buttonText Text to appear on button
     * @param imagePath Path to image for button
     * @return returns a JButton
     */
    private JButton JButtonImage(String buttonText, String imagePath) {
        try {
            //creates new button
            JButton newBtn = new JButton(buttonText, new ImageIcon(imagePath));
            //initializes buttons and sets them to newBtn
            newBtn.addActionListener(this);
            //sets size of button
            newBtn.setPreferredSize(new Dimension(100, 60));
            // Format text position
            newBtn.setVerticalTextPosition(AbstractButton.BOTTOM);
            newBtn.setHorizontalTextPosition(AbstractButton.CENTER);

            return newBtn;
            //catches Error
        } catch (Exception e) {
            //Dialog box showing Error
            JOptionPane.showMessageDialog(pnlBtn,
                    "Error in loading image: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Handle events of keys pressed
     * @param e new KeyEvent
     */
    public void keyPressed(KeyEvent e) {
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
                    GUI_Frame gui = new GUI_Frame("CAB230 - VECTOR DRAWING PROGRAM NEW WINDOW", vec.get());
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
        if(keyCodeNew == KeyEvent.VK_H){
            //Help KeyBind
            if(!pressed.isEmpty()) {

                JOptionPane.showMessageDialog(pnlBtn,
                        "Basic KeyBinds: H for Help, S for Save, Z for Undo",
                        "Help",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }



    }

    /**
     * Handle event when key is released
     * @param e new KeyEvent
     */
    public void keyReleased(KeyEvent e){
        if(!pressed.isEmpty()) {
            pressed.remove(0);
        }

    }

    /**
     *
     * @param e
     */
    public void keyTyped(KeyEvent e){
    }

    /**
     * Build the top panel of the frame
     */
    private void createLayoutHistoryTopPanel(){
        //History Panel
        historyPanel = createPanel(new Color(0xBFE3FF));
        undoButton = JButtonImage("Undo", "./img/buttons/undo.png");
        historyPanel.setLayout(new GridBagLayout());
        undoHistoryComboBox = new JComboBox();
        historyPanel.setComponentOrientation(
                ComponentOrientation.LEFT_TO_RIGHT);
        historyPanel.add(undoButton);
        historyPanel.add(undoHistoryComboBox);

        undoHistoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                focus = false;
                JComboBox cb = (JComboBox)e.getSource();
                String selectedItem = (String)cb.getSelectedItem();
                String[] split = selectedItem.split(" ");
                int historyIndex = Integer.parseInt(split[0]);
                // remove all items in array up to history index
                display.trimToIndex(historyIndex);
                requestFocus();
                requestFocusInWindow();


            }
        });

    }

    /**
     * Build the left side panel with control buttons.
     */
    private void createLayoutButtonPanel() {
        //create side button panel
        pnlBtn = createPanel(new Color(0xBFE3FF));
        String imgPath = "./img/";
        //initializes all buttons
        PlotButton = JButtonImage("Plot",imgPath + "buttons/Plot.jpg");
        LineButton = JButtonImage("Line",imgPath + "buttons/line.png");
        RectangleButton = JButtonImage("Box",imgPath + "buttons/rectangle.png");
        EllipseButton = JButtonImage("Ellipse",imgPath + "buttons/ellipse.png");
        PolygonButton = JButtonImage("Polygon", imgPath + "buttons/polygon.png");
        FillButton = JButtonImage("Fill Colour", imgPath + "buttons/fill.png");
        PenButton = JButtonImage("Pen Colour", imgPath + "buttons/pen.png");
        //Layout settings for side buttons
        pnlBtn.setLayout(new GridBagLayout());
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

    /**
     * Adds specified component to specified JPanel
     * @param jp the JPanel to add to
     * @param c the Component to add
     * @param constraints any GridBag Constraints
     * @param x x position on panel
     * @param y y position on panel
     * @param w width
     * @param h height
     */
    private void addToPanel(JPanel jp, Component c, GridBagConstraints constraints,
                            int x, int y, int w, int h) {
        //adding buttons to panel
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        jp.add(c, constraints);
    }

    /**
     * Build the top menu of the frame
     */
    private void createTopMenu() {
        //top navigation bar buttons
        undo = new JMenuItem("Undo");
        fileNew = new JMenuItem("New");
        fileOpen = new JMenuItem("Open");
        fileSave = new JMenuItem("Save");
        fileSaveAs = new JMenuItem("Save As");
        gridBtn = new JMenuItem("Grid");
        helpBtn = new JMenuItem("Help");

        //gridButt = new JMenuItem("Grid for real");
        //adding action listeners to MenuBar Buttons
        gridBtn.addActionListener(this);
        helpBtn.addActionListener(this);

        undo.addActionListener(this);
        fileNew.addActionListener(this);
        fileOpen.addActionListener(this);
        fileSave.addActionListener(this);
        fileSaveAs.addActionListener(this);
        //gridButt = new JMenuItem("Grid");


        JMenuBar mb = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        help = new JMenu("Help");
        grid = new JMenu("Grid");

        file.add(fileOpen);
        file.add(fileSave);
        file.add(fileNew);
        file.add(fileSaveAs);
        //add these buttons to
        edit.add(undo);
        grid.add(gridBtn);
        help.add(helpBtn);
        //help.add(gridButt);
        mb.add(file);
        mb.add(edit);
        mb.add(help);
        mb.add(grid);

        //add to GUI and set visible
        add(mb);
        setJMenuBar(mb);
        setVisible(true);
    }

    /**
     * Build the full GUI
     */
    private void createGUI() {
        String imgPath = "./img/";
        //set size of GUI
        setSize(WIDTH, HEIGHT);
        //setPreferredSize(new Dimension(1000, 3000));
        //close operation on exit button click
        setLayout(new BorderLayout());
        //create side button panel
        pnlBtn = createPanel(Color.GRAY);
        //Drawing canvas creation
        JPanel pnlDisplay = createPanel(Color.WHITE);
        //initializes all buttons
        PlotButton = JButtonImage("Plot",imgPath + "buttons/Plot.jpg");
        LineButton = JButtonImage("Line",imgPath + "buttons/line.png");
        RectangleButton = JButtonImage("Box",imgPath + "buttons/rectangle.png");
        EllipseButton = JButtonImage("Ellipse",imgPath + "buttons/ellipse.png");
        PolygonButton = JButtonImage("Polygon", imgPath + "buttons/polygon.png");
        FillButton = JButtonImage("Fill Colour", imgPath + "buttons/fill.png");
        PenButton = JButtonImage("Pen Colour", imgPath + "buttons/pen.png");

        createLayoutButtonPanel();
        createLayoutHistoryTopPanel();

        getContentPane().add(pnlBtn, BorderLayout.WEST);
        getContentPane().add(historyPanel, BorderLayout.NORTH);
        getContentPane().add(pnlDisplay, BorderLayout.CENTER);
        display = new GraphicsCanvas();
        display.setComboBox(undoHistoryComboBox);
        createTopMenu();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(numWindows < 1) {
                    System.out.println("WindowClosingDemo.windowClosing");
                    System.exit(0);
                }else{
                    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    System.out.println(numWindows);
                    numWindows--;
                }

            }
        });
        // add mouse listener
        mouseDraw.setCanvas(display);
        display.addMouseListener(mouseDraw);
        display.addMouseMotionListener(mouseDraw);

        display.setBorder(BorderFactory.createEtchedBorder());
        display.setBounds(5, 5, 360, 320);
        add(display, BorderLayout.CENTER);

//        areDisplay();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        //add keyboard listeners and focus
        addKeyListener(this);
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Dimension size = display.getSize();

                if(size.width != size.height){
                    //display.setSize(size.width, size.width);
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
                        display.getSize().width, prevSreenWidth,
                        display.getSize().height, prevScreenHeight);
                prevSreenWidth = display.getSize().width;
                prevScreenHeight = display.getSize().height;
            }
        });


        setFocusable(true);
        requestFocus();
        repaint();
        setVisible(true);
        if(!shapes.isEmpty()){
            for(AdvancedShape shape: shapes){
                display.add(shape);

            }
        }

    }


    /**
     * sets the type of shape the user wants to use
     * @param type
     */
    private void setAction(ShapeType type){
        mouseDraw.setType(type);
        requestFocus();
        setFocusable(true);
        requestFocus();
    }

    /**
     * Set the Pen and Fill color with a colorChooser
     * @param setFill true -> setFill, false -> setPen
     */
    private void setFillColor(boolean setFill){
        //sets the color
        Color newColor = JColorChooser.showDialog(this, "Select a color", initialcolor);
        if (newColor == null) {
            if (setFill){
                // set transparent if no color selected
                newColor = new Color(0,0,0,0);
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

    /**
     * Save a vec file with a file chooser dialog
     */
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

    /**
     * Open a vec file with a file chooser dialog
     */
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
            GUI_Frame gui = new GUI_Frame("CAB230 - VECTOR DRAWING PROGRAM NEW WINDOW", vec.get());
            SwingUtilities.invokeLater(gui);
        }
    }

    /**
     * Undo the last shape added to the array
     */
    private void undo(){
        Shape latest = display.getLatest();
        if (latest != null) {
            while (display.getLatest() == latest) {
                display.clearLast();
            }
        }
    }

    /**
     * Display the help message popup
     */
    private void helpMessage(){
        //Dialog help Message
        JOptionPane.showMessageDialog(pnlBtn,
                "Basic KeyBinds: H for Help, S for Save, Z for Undo",
                "Help",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Set the current grid snap with a dialog popup
     */
    private void gridInput(){
        String errorMessage = "";
        try {
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
        catch(Exception errorM){

        }
    }

    /**
     * Ensures that screen panel is always square
     */
    private void forceScreenSize(){
        int width = display.getSize().width;
        int height = display.getSize().height;
        if (height != width) {
            // set to lowest if not
            if (height > width) display.setSize(width, width);
            else display.setSize(height, height);
        }
    }
    /**
     * Handle GUI controls on select
     * @param e
     */
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
        else if (src == fileNew) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            SwingUtilities.invokeLater(new GUI_Frame("CAB230 - VECTOR DRAWING PROGRAM NEW WINDOW"));
            numWindows++;
            System.out.println(numWindows);
        }
        // WINDOW REFRESH
        if (e.getSource()==timer) {
            repaint(); // repaint every timer expiry\
            forceScreenSize();


        }
    }

    @Override
    public void run() {
        createGUI();
    }

}