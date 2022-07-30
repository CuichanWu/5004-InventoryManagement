import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * This GUI is the notice window
 */
public class WindowNotice extends JFrame implements ActionListener  {
    private ActionEvent e;
    public WindowNotice() throws ParseException {
        DefaultUI ui = new DefaultUI("Notifications", this);
        setVisible(true);

        // Center stock info
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3,1));

        // 3-1 Low Stock Panel
        JPanel lowStock = new JPanel();
        // show title
        JLabel titleLow = new JLabel(" Low Stock");
        titleLow.setFont(new Font(DefaultUI.TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, DefaultUI.TITLE_SIZE));
        titleLow.setForeground(DefaultUI.TITLE_COLOR);
        lowStock.setBackground(DefaultUI.MAIN_BACKGROUND);
        lowStock.add(titleLow, BorderLayout.NORTH);
        lowStock.setBorder(BorderFactory.createLineBorder(DefaultUI.GREEN_THEME));
        // show items
        Stock newStock = new Stock();
        ArrayList<FoodItem> lowStockList = newStock.getLowStockItems(Stock.StockType.ALL);
        int sizeOfLowStockItems = lowStockList.size();
        lowStock.setLayout(new GridLayout(sizeOfLowStockItems + 1, 1));
        JButton[] lowStockItemButtons = new JButton[sizeOfLowStockItems];
        for (int i = 0; i < sizeOfLowStockItems; i++) {
            String itemText = lowStockList.get(i).toString();
            lowStockItemButtons[i] = new JButton(itemText);
            lowStock.add(lowStockItemButtons[i]);
        }
        infoPanel.add(lowStock);

        // 3-2 Expired Panel
        JPanel expired = new JPanel();
        // show title
        JLabel titleExpired = new JLabel(" Expired");
        titleExpired.setFont(new Font(DefaultUI.TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, DefaultUI.TITLE_SIZE));
        titleExpired.setForeground(DefaultUI.TITLE_COLOR);
        expired.setBackground(DefaultUI.MAIN_BACKGROUND);
        expired.add(titleExpired, BorderLayout.NORTH);
        expired.setBorder(BorderFactory.createLineBorder(DefaultUI.GREEN_THEME));
        // show items
        ArrayList<FoodItem> expiredList = newStock.getExpiredItems(Stock.StockType.ALL);
        int sizeOfExpiredItems = expiredList.size();
        expired.setLayout(new GridLayout(sizeOfExpiredItems + 1, 1));
        JButton[] expiredItemButtons = new JButton[sizeOfExpiredItems];
        for (int i = 0; i < sizeOfExpiredItems; i++) {
            String itemText = expiredList.get(i).toString();
            expiredItemButtons[i] = new JButton(itemText);
            expired.add(expiredItemButtons[i]);
        }
        infoPanel.add(expired);

        // 3-3 Expiring Panel
        JPanel expiring = new JPanel();

        // show title
        JLabel titleExpiring = new JLabel(" Expiring soon");
        titleExpiring.setFont(new Font(DefaultUI.TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, DefaultUI.TITLE_SIZE));
        titleExpiring.setForeground(DefaultUI.TITLE_COLOR);
        expiring.setBackground(DefaultUI.MAIN_BACKGROUND);
        expiring.add(titleExpiring, BorderLayout.NORTH);
        expiring.setBorder(BorderFactory.createLineBorder(DefaultUI.GREEN_THEME));

        // show items
        Stock stock = new Stock();
        ArrayList<FoodItem> showinglist = stock.getAlmostExpiredItems(Stock.StockType.ALL);
        int size = showinglist.size();

        JPanel expiringItemsPanel = new JPanel();
        expiringItemsPanel.setLayout(new GridLayout(5, 1));
        expiringItemsPanel.setBackground(DefaultUI.MAIN_BACKGROUND);
        for (int i = 0; i < size; i++) {
            JPanel itemPanel = new JPanel(new GridLayout(1, 4));
            itemPanel.setBackground(DefaultUI.WHITE_COLOR);
            // a. remove
            ImageIcon newIcon = new ImageIcon("./icons/delete_g.png");
            Image img = newIcon.getImage();
            Image newImg = img.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            ImageIcon removeIcon = new ImageIcon(newImg);

            JButton removeButton = new JButton(removeIcon);
            removeButton.setActionCommand("remove" + i);
            removeButton.setOpaque(true);
            removeButton.setBorderPainted(false);
            removeButton.addActionListener(this);
            removeButton.setBackground(DefaultUI.WHITE_COLOR);
            itemPanel.add(removeButton);

            // b. Name
            JButton nameButton = new JButton(showinglist.get(i).getName());
            nameButton.setActionCommand("View" + i);
            nameButton.setFont(new Font(DefaultUI.TITLE_FONT, Font.BOLD, 13));
            nameButton.setHorizontalAlignment(SwingConstants.LEFT);
            nameButton.setOpaque(true);
            nameButton.setBorderPainted(false);
            nameButton.setBackground(DefaultUI.WHITE_COLOR);
            itemPanel.add(nameButton);

            // c. Quantity
            JLabel qtyLabel = new JLabel(String.valueOf(showinglist.get(i).getQuantity()));
            qtyLabel.setFont(new Font(DefaultUI.TITLE_FONT, Font.PLAIN, 11));
            itemPanel.add(qtyLabel);

            // d. Expiration
            JLabel expirationLabel = new JLabel(showinglist.get(i).getExpirationToString());
            expirationLabel.setFont(new Font(DefaultUI.TITLE_FONT, Font.PLAIN, 11));
            itemPanel.add(expirationLabel);

            expiringItemsPanel.add(itemPanel);
        }
        expiring.add(expiringItemsPanel);

        infoPanel.add(expiring);

        add(infoPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e){
        String actionCommand = e.getActionCommand();
        if (actionCommand.startsWith("item")) {
            setVisible(false);
            try {
                AddWindow aNewWindow = new AddWindow();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
        else
            System.out.println("Unexpected error.");
    }
}