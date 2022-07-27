import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

/**
 * This GUI is the basic menu
 */
public class FridgeGUIwithAction extends JFrame implements ActionListener  {
    public static final int WIDTH = 350;
    public static final int HEIGHT = 750;
    private ActionEvent e;
    public static final Color mainBackground = new Color(242, 245, 237);
    public static final Color menuBackground = new Color(232, 240, 213);
    public static final Color titleColor = new Color(122, 156, 87);
    public static final Color redColor = new Color(223, 185, 182);
    public static String titleFont = "San Francisco";
    public static String textFont = "Arial";
    public static int titleSize = 18;
    public static int textSize = 14;
    public static int menuSize = 12;
    public static Font menuFormat = new Font(titleFont, Font.PLAIN, menuSize);


    public FridgeGUIwithAction(){
        super("My Fridge"); //title name
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //top menu
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,3));
        buttonPanel.setBackground(menuBackground);

        JButton addButton = new JButton("➕ Add");
        addButton.setFont(menuFormat);
        addButton.setBackground(menuBackground);
        addButton.setOpaque(true);
        addButton.setBorderPainted(false);
        addButton.addActionListener(this);
        buttonPanel.add(addButton);

        JButton viewButton = new JButton("👁 Stock");
        viewButton.setFont(menuFormat);
        viewButton.setBackground(menuBackground);
        viewButton.setOpaque(true);
        viewButton.setBorderPainted(false);
        viewButton.addActionListener(this);
        buttonPanel.add(viewButton);

        JButton recipeButton = new JButton("📖 Recipe");
        recipeButton.setFont(menuFormat);
        recipeButton.setBackground(menuBackground);
        recipeButton.setOpaque(true);
        recipeButton.setBorderPainted(false);
        recipeButton.addActionListener(this);
        buttonPanel.add(recipeButton);

        add(buttonPanel, BorderLayout.SOUTH);

        //center stock info
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3,1));

        JPanel lowStock = new JPanel();
        lowStock.setLayout(new BorderLayout());
        JLabel titleLow = new JLabel(" Low Stock");
        titleLow.setFont(new Font(titleFont, Font.LAYOUT_LEFT_TO_RIGHT, titleSize));
        titleLow.setForeground(titleColor);
        lowStock.setBackground(mainBackground);
        lowStock.add(titleLow, BorderLayout.NORTH);
        //details put in center/bottom -- not finished yet
        lowStock.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        infoPanel.add(lowStock);

        JPanel expired = new JPanel();
        expired.setLayout(new BorderLayout());
        JLabel titleExpired = new JLabel(" Expired");
        titleExpired.setFont(new Font(titleFont, Font.LAYOUT_LEFT_TO_RIGHT, titleSize));
        titleExpired.setForeground(titleColor);
        expired.setBackground(mainBackground);
        expired.add(titleExpired, BorderLayout.NORTH);
        //details put in center/bottom -- not finished yet
        expired.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        infoPanel.add(expired);

        JPanel expiring = new JPanel();
        expiring.setLayout(new BorderLayout());
        JLabel titleExpiring = new JLabel(" Expiring soon");
        titleExpiring.setFont(new Font(titleFont, Font.LAYOUT_LEFT_TO_RIGHT, titleSize));
        titleExpiring.setForeground(titleColor);
        expiring.setBackground(mainBackground);
        expiring.add(titleExpiring, BorderLayout.NORTH);
        //details put in center/bottom -- not finished yet
        expiring.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        infoPanel.add(expiring);

        add(infoPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e){
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("➕ Add")) {
            //System.out.println("add item");
            setVisible(false); //can keep the new window opened only (looks like close the previous window)
            AddWindow aNewWindow = new AddWindow();
        }
        else if (actionCommand.equals("👁 Stock")) {
            setVisible(false);
            try {
                StockWindow aNewWindow = new StockWindow();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
        //System.out.println("view item");
        else if (actionCommand.equals("📖 Recipe")) {
            setVisible(false);
            RecipeWindow aNewWindow = new RecipeWindow();
        }
        //System.out.println("recipes");
        else
            System.out.println("Unexpected error.");
    }


    public static void main(String[] args) {
        FridgeGUIwithAction gui = new FridgeGUIwithAction();
        gui.setVisible(true);
    }
}