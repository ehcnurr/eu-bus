import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class BusStatusUI {

    private final BusStatus busStatus;

    public BusStatusUI(BusStatus busStatus) {
        this.busStatus = busStatus;

        styleTable();
        styleComponents();
    }

    private void styleComponents() {

        JTable tblHistory = busStatus.getTblHistory();
        JPanel goldLinePanel = busStatus.getGoldLinePanel();
        JTabbedPane tabbedPane1 = busStatus.getTabbedPane1();

        tblHistory.setRowHeight(40);

        // Set table font
        tblHistory.setFont(
                new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 10)
        );

        // Set table header font
        tblHistory.getTableHeader().setFont(
                new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 10)
        );

        // Set column widths
        tblHistory.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        tblHistory.getColumnModel().getColumn(0).setPreferredWidth(75);
        tblHistory.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblHistory.getColumnModel().getColumn(2).setPreferredWidth(105);
        tblHistory.getColumnModel().getColumn(3).setPreferredWidth(105);
        tblHistory.getColumnModel().getColumn(4).setPreferredWidth(95);
        tblHistory.getColumnModel().getColumn(5).setPreferredWidth(85);
        tblHistory.getColumnModel().getColumn(6).setPreferredWidth(85);
        tblHistory.getColumnModel().getColumn(7).setPreferredWidth(85);
        tblHistory.getColumnModel().getColumn(8).setPreferredWidth(85);

        tblHistory.getTableHeader().setPreferredSize(
                new java.awt.Dimension(0, 50)
        );

        goldLinePanel.setBackground(new Color(212, 175, 55));
        goldLinePanel.setOpaque(true);

        goldLinePanel.setPreferredSize(new Dimension(0, 2));
        goldLinePanel.setMinimumSize(new Dimension(0, 2));
        goldLinePanel.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 2)
        );

        // ENVERGA TABBED PANE DESIGN
        tabbedPane1.setUI(new EnvergaTabbedPaneUI());

        tabbedPane1.setBackground(
                new Color(247, 245, 242)
        );

        tabbedPane1.setForeground(
                new Color(128, 0, 32)
        );

        tabbedPane1.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        tabbedPane1.setBorder(
                BorderFactory.createEmptyBorder()
        );

        tabbedPane1.setOpaque(false);

        tabbedPane1.setTabLayoutPolicy(
                JTabbedPane.SCROLL_TAB_LAYOUT
        );
    }

    private static class EnvergaTabbedPaneUI extends BasicTabbedPaneUI {

        private final Color burgundy = new Color(128, 0, 32);
        private final Color gold = new Color(212, 175, 55);
        private final Color warmWhite = new Color(247, 245, 242);
        private final Color inactiveText = new Color(120, 115, 115);

        @Override
        protected void installDefaults() {
            super.installDefaults();

            tabAreaInsets = new Insets(0, 8, 0, 8);
            tabInsets = new Insets(10, 20, 10, 20);

            contentBorderInsets = new Insets(0, 0, 0, 0);
        }

        // TAB BACKGROUND
        @Override
        protected void paintTabBackground(
                Graphics g,
                int tabPlacement,
                int tabIndex,
                int x,
                int y,
                int w,
                int h,
                boolean isSelected) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            if (isSelected) {
                g2.setColor(burgundy);
            } else {
                g2.setColor(warmWhite);
            }

            g2.fillRoundRect(
                    x + 2,
                    y + 2,
                    w - 4,
                    h + 5,
                    14,
                    14
            );

            g2.dispose();
        }

        // REMOVE DEFAULT TAB BORDER
        @Override
        protected void paintTabBorder(
                Graphics g,
                int tabPlacement,
                int tabIndex,
                int x,
                int y,
                int w,
                int h,
                boolean isSelected) {
            // No default border
        }

        // TAB TEXT COLOR AND FONT
        @Override
        protected void paintText(
                Graphics g,
                int tabPlacement,
                Font font,
                FontMetrics metrics,
                int tabIndex,
                String title,
                Rectangle textRect,
                boolean isSelected) {

            Font tabFont = new Font(
                    "Segoe UI",
                    Font.BOLD,
                    14
            );

            g.setFont(tabFont);

            g.setColor(
                    isSelected ? Color.WHITE : inactiveText
            );

            FontMetrics fm = g.getFontMetrics(tabFont);

            int textX = textRect.x +
                    (textRect.width - fm.stringWidth(title)) / 2;

            int textY = textRect.y +
                    (textRect.height - fm.getHeight()) / 2 +
                    fm.getAscent();

            g.drawString(title, textX, textY);
        }

        // GOLD DIVIDER UNDER THE TABS
        @Override
        protected void paintContentBorder(
                Graphics g,
                int tabPlacement,
                int selectedIndex) {

            int y = tabPane.getInsets().top +
                    calculateTabAreaHeight(
                            tabPlacement,
                            runCount,
                            maxTabHeight
                    );

            g.setColor(gold);

            g.fillRect(
                    0,
                    y,
                    tabPane.getWidth(),
                    2
            );
        }
    }

    private void styleTable() {

        JTable tblHistory = busStatus.getTblHistory();

        Color burgundy = new Color(128, 0, 32);
        Color gold = new Color(212, 175, 55);
        Color beige = new Color(247, 245, 242);

        // TABLE BACKGROUND
        tblHistory.setBackground(Color.WHITE);

        tblHistory.setForeground(
                new Color(55, 55, 55)
        );

        tblHistory.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        tblHistory.setRowHeight(48);

        // REMOVE DEFAULT SELECTION COLORS
        tblHistory.setSelectionBackground(
                new Color(245, 231, 197)
        );

        tblHistory.setSelectionForeground(burgundy);

        // TABLE GRID
        tblHistory.setShowVerticalLines(false);
        tblHistory.setShowHorizontalLines(true);

        tblHistory.setGridColor(
                new Color(225, 220, 210)
        );

        tblHistory.setIntercellSpacing(
                new Dimension(0, 1)
        );

        // TABLE HEADER
        JTableHeader header = tblHistory.getTableHeader();

        header.setPreferredSize(new Dimension(0, 55));
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));
        header.setBackground(burgundy);
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false);
        header.setResizingAllowed(false);

        // CUSTOM HEADER RENDERER
        DefaultTableCellRenderer headerRenderer =
                new DefaultTableCellRenderer() {

                    @Override
                    public Component getTableCellRendererComponent(
                            JTable table,
                            Object value,
                            boolean isSelected,
                            boolean hasFocus,
                            int row,
                            int column) {

                        JLabel label = new JLabel(
                                value == null ? "" : value.toString()
                        );

                        label.setHorizontalAlignment(
                                SwingConstants.CENTER
                        );

                        label.setVerticalAlignment(
                                SwingConstants.CENTER
                        );

                        label.setFont(
                                new Font(
                                        "Segoe UI",
                                        Font.BOLD,
                                        12
                                )
                        );

                        label.setForeground(Color.WHITE);
                        label.setBackground(burgundy);

                        label.setOpaque(true);

                        label.setBorder(
                                BorderFactory.createMatteBorder(
                                        0, 0, 2, 0, gold
                                )
                        );

                        return label;
                    }
                };

        for (int i = 0; i < tblHistory.getColumnCount(); i++) {
            tblHistory.getColumnModel()
                    .getColumn(i)
                    .setHeaderRenderer(headerRenderer);
        }

        // ALTERNATING ROW COLORS
        DefaultTableCellRenderer rowRenderer =
                new DefaultTableCellRenderer() {

                    @Override
                    public Component getTableCellRendererComponent(
                            JTable table,
                            Object value,
                            boolean isSelected,
                            boolean hasFocus,
                            int row,
                            int column) {

                        JLabel label = (JLabel)
                                super.getTableCellRendererComponent(
                                        table,
                                        value,
                                        isSelected,
                                        hasFocus,
                                        row,
                                        column
                                );

                        label.setHorizontalAlignment(
                                SwingConstants.CENTER
                        );

                        label.setFont(
                                new Font(
                                        "Segoe UI",
                                        Font.PLAIN,
                                        13
                                )
                        );

                        label.setForeground(
                                new Color(55, 55, 55)
                        );

                        if (isSelected) {

                            label.setBackground(
                                    new Color(245, 231, 197)
                            );

                            label.setForeground(burgundy);

                        } else if (row % 2 == 0) {

                            label.setBackground(Color.WHITE);

                        } else {

                            label.setBackground(beige);
                        }

                        label.setBorder(
                                BorderFactory.createEmptyBorder(
                                        5, 3, 5, 3
                                )
                        );

                        return label;
                    }
                };

        for (int i = 0; i < tblHistory.getColumnCount(); i++) {
            tblHistory.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(rowRenderer);
        }

        tblHistory.setFillsViewportHeight(true);
    }
}