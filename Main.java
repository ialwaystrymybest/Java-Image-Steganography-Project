import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.*;

public class Main extends JFrame {

    private boolean isDarkTheme = true;
    private JToggleButton themeToggle;

    // ── Dark Palette ────────────────────────────────────────────────
    private static final Color D_BG       = new Color(24, 24, 28);
    private static final Color D_SURFACE  = new Color(36, 36, 42);
    private static final Color D_INPUT    = new Color(48, 48, 56);
    private static final Color D_BORDER   = new Color(65, 65, 75);
    private static final Color D_TEXT     = new Color(235, 235, 240);
    private static final Color D_TEXTDIM  = new Color(160, 160, 175);
    private static final Color D_ACCENT   = new Color(99, 102, 241);
    private static final Color D_GREEN    = new Color(52, 211, 153);
    private static final Color D_RED      = new Color(239, 68, 68);

    // ── Light Palette ───────────────────────────────────────────────
    private static final Color L_BG       = new Color(245, 246, 250);
    private static final Color L_SURFACE  = new Color(255, 255, 255);
    private static final Color L_INPUT    = new Color(240, 242, 247);
    private static final Color L_BORDER   = new Color(205, 210, 220);
    private static final Color L_TEXT     = new Color(25, 25, 35);
    private static final Color L_TEXTDIM  = new Color(100, 105, 120);
    private static final Color L_ACCENT   = new Color(79, 70, 229);
    private static final Color L_GREEN    = new Color(16, 185, 129);
    private static final Color L_RED      = new Color(220, 38, 38);

    // Active colors
    private Color bg, surface, input, border, text, textDim, accent, green, red;

    // ── Entry Point ─────────────────────────────────────────────────
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main gui = new Main();
            gui.setVisible(true);
        });
    }

    public Main() {
        setTitle("LSB Steganography");
        setSize(780, 580);
        setMinimumSize(new Dimension(600, 450));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        loadPalette();
        installUIDefaults();
        buildUI();
    }

    private void loadPalette() {
        if (isDarkTheme) {
            bg = D_BG; surface = D_SURFACE; input = D_INPUT; border = D_BORDER;
            text = D_TEXT; textDim = D_TEXTDIM; accent = D_ACCENT;
            green = D_GREEN; red = D_RED;
        } else {
            bg = L_BG; surface = L_SURFACE; input = L_INPUT; border = L_BORDER;
            text = L_TEXT; textDim = L_TEXTDIM; accent = L_ACCENT;
            green = L_GREEN; red = L_RED;
        }
    }

    // Force every Swing component type to use our colors via UIManager
    private void installUIDefaults() {
        UIManager.put("Panel.background", bg);
        UIManager.put("Panel.foreground", text);
        UIManager.put("Label.foreground", text);
        UIManager.put("Label.background", bg);
        UIManager.put("Button.background", input);
        UIManager.put("Button.foreground", text);
        UIManager.put("Button.select", border);
        UIManager.put("ToggleButton.background", input);
        UIManager.put("ToggleButton.foreground", text);
        UIManager.put("ToggleButton.select", border);
        UIManager.put("TextField.background", input);
        UIManager.put("TextField.foreground", text);
        UIManager.put("TextField.caretForeground", text);
        UIManager.put("TextField.inactiveForeground", textDim);
        UIManager.put("TextArea.background", input);
        UIManager.put("TextArea.foreground", text);
        UIManager.put("TextArea.caretForeground", text);
        UIManager.put("TextArea.inactiveForeground", textDim);
        UIManager.put("ScrollPane.background", input);
        UIManager.put("ScrollBar.background", surface);
        UIManager.put("ScrollBar.thumb", border);
        UIManager.put("ScrollBar.thumbShadow", border);
        UIManager.put("ScrollBar.thumbHighlight", border);
        UIManager.put("ScrollBar.track", surface);
        UIManager.put("Viewport.background", input);
        UIManager.put("TabbedPane.background", bg);
        UIManager.put("TabbedPane.foreground", text);
        UIManager.put("TabbedPane.selected", surface);
        UIManager.put("TabbedPane.contentAreaColor", surface);
        UIManager.put("TabbedPane.tabAreaBackground", bg);
        UIManager.put("TabbedPane.selectHighlight", border);
        UIManager.put("TabbedPane.darkShadow", border);
        UIManager.put("TabbedPane.shadow", border);
        UIManager.put("TabbedPane.light", surface);
        UIManager.put("TabbedPane.highlight", surface);
        UIManager.put("TabbedPane.focus", accent);
        UIManager.put("TabbedPane.unselectedBackground", bg);
        UIManager.put("OptionPane.background", surface);
        UIManager.put("OptionPane.foreground", text);
        UIManager.put("OptionPane.messageForeground", text);
        UIManager.put("FileChooser.background", surface);
        UIManager.put("FileChooser.foreground", text);
        UIManager.put("ComboBox.background", input);
        UIManager.put("ComboBox.foreground", text);
        UIManager.put("List.background", input);
        UIManager.put("List.foreground", text);
        UIManager.put("Table.background", input);
        UIManager.put("Table.foreground", text);
        UIManager.put("Tree.background", input);
        UIManager.put("Tree.foreground", text);
        UIManager.put("Tree.textBackground", input);
        UIManager.put("Tree.textForeground", text);
        UIManager.put("Menu.background", surface);
        UIManager.put("Menu.foreground", text);
        UIManager.put("MenuBar.background", surface);
        UIManager.put("MenuBar.foreground", text);
        UIManager.put("MenuItem.background", surface);
        UIManager.put("MenuItem.foreground", text);
        UIManager.put("PopupMenu.background", surface);
        UIManager.put("PopupMenu.foreground", text);
        UIManager.put("ToolTip.background", surface);
        UIManager.put("ToolTip.foreground", text);
        UIManager.put("control", surface);
        UIManager.put("controlText", text);
        UIManager.put("text", text);
        UIManager.put("textText", text);
        UIManager.put("textHighlight", accent);
        UIManager.put("textHighlightText", Color.WHITE);
        UIManager.put("window", bg);
        UIManager.put("windowText", text);
        UIManager.put("info", surface);
        UIManager.put("infoText", text);
    }

    // ── Build UI ────────────────────────────────────────────────────
    private void buildUI() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(bg);
        setContentPane(root);

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(surface);
        header.setBorder(new EmptyBorder(12, 20, 12, 20));

        JLabel title = new JLabel("LSB Steganography");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(text);
        header.add(title, BorderLayout.WEST);

        themeToggle = new JToggleButton(isDarkTheme ? "Light Theme" : "Dark Theme");
        themeToggle.setSelected(isDarkTheme);
        themeToggle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        themeToggle.setFocusPainted(false);
        themeToggle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        themeToggle.setBorder(new EmptyBorder(8, 18, 8, 18));
        themeToggle.setBackground(input);
        themeToggle.setForeground(text);
        themeToggle.addActionListener(e -> switchTheme());
        header.add(themeToggle, BorderLayout.EAST);

        root.add(header, BorderLayout.NORTH);

        // Tabs
        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabs.setForeground(text);
        tabs.setBackground(bg);
        tabs.setBorder(new EmptyBorder(8, 12, 12, 12));
        tabs.addTab("  Embed Message  ", buildEmbedTab());
        tabs.addTab("  Extract Message  ", buildExtractTab());
        root.add(tabs, BorderLayout.CENTER);
    }

    private void switchTheme() {
        isDarkTheme = themeToggle.isSelected();
        themeToggle.setText(isDarkTheme ? "Light Theme" : "Dark Theme");
        loadPalette();
        installUIDefaults();
        buildUI();
        SwingUtilities.updateComponentTreeUI(this);
        revalidate();
        repaint();
    }

    // ── Embed Tab ───────────────────────────────────────────────────
    private JPanel buildEmbedTab() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(surface);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(border),
            new EmptyBorder(20, 20, 20, 20)
        ));

        // Input image
        card.add(label("Input Image"));
        card.add(Box.createVerticalStrut(6));
        JTextField inputField = textField();
        JButton browseIn = secondaryBtn("Browse");
        card.add(fieldRow(inputField, browseIn));

        card.add(Box.createVerticalStrut(14));

        // Output image
        card.add(label("Output Image"));
        card.add(Box.createVerticalStrut(6));
        JTextField outputField = textField();
        JButton browseOut = secondaryBtn("Browse");
        card.add(fieldRow(outputField, browseOut));

        card.add(Box.createVerticalStrut(14));

        // Message
        card.add(label("Secret Message"));
        card.add(Box.createVerticalStrut(6));
        JTextArea msgArea = textArea(6);
        JScrollPane sp = scroll(msgArea);
        sp.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(sp);

        card.add(Box.createVerticalStrut(14));

        // Status
        JLabel status = new JLabel(" ");
        status.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        status.setForeground(textDim);
        status.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(status);

        card.add(Box.createVerticalStrut(8));

        // Button
        JButton embedBtn = accentBtn("Embed Message");
        embedBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(embedBtn);

        // Listeners
        browseIn.addActionListener(e -> pickOpen(inputField));
        browseOut.addActionListener(e -> pickSave(outputField));

        embedBtn.addActionListener(e -> {
            String in = inputField.getText().trim();
            String out = outputField.getText().trim();
            String msg = msgArea.getText().trim();
            if (in.isEmpty() || out.isEmpty() || msg.isEmpty()) {
                setStatus(status, "Please fill in all fields.", true); return;
            }
            File f = new File(in);
            if (!f.exists() || !f.isFile()) {
                setStatus(status, "Input image does not exist: " + in, true); return;
            }
            embedBtn.setEnabled(false);
            embedBtn.setText("Processing...");
            setStatus(status, "Embedding message...", false);
            new Thread(() -> {
                try {
                    EmbedLSB.Embed(f, msg, out);
                    SwingUtilities.invokeLater(() -> {
                        setStatus(status, "Message hidden successfully! Saved to: " + out, false);
                        status.setForeground(green);
                    });
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> setStatus(status, "Error: " + ex.getMessage(), true));
                } finally {
                    SwingUtilities.invokeLater(() -> { embedBtn.setEnabled(true); embedBtn.setText("Embed Message"); });
                }
            }).start();
        });

        return card;
    }

    // ── Extract Tab ─────────────────────────────────────────────────
    private JPanel buildExtractTab() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(surface);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(border),
            new EmptyBorder(20, 20, 20, 20)
        ));

        // Image path
        card.add(label("Image with Hidden Message"));
        card.add(Box.createVerticalStrut(6));
        JTextField inputField = textField();
        JButton browseIn = secondaryBtn("Browse");
        card.add(fieldRow(inputField, browseIn));

        card.add(Box.createVerticalStrut(14));

        // Status
        JLabel status = new JLabel(" ");
        status.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        status.setForeground(textDim);
        status.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(status);

        card.add(Box.createVerticalStrut(8));

        // Button
        JButton extractBtn = accentBtn("Extract Message");
        extractBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(extractBtn);

        card.add(Box.createVerticalStrut(14));

        // Output
        card.add(label("Extracted Message"));
        card.add(Box.createVerticalStrut(6));
        JTextArea outArea = textArea(8);
        outArea.setEditable(false);
        JScrollPane sp = scroll(outArea);
        sp.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(sp);

        // Listeners
        browseIn.addActionListener(e -> pickOpen(inputField));

        extractBtn.addActionListener(e -> {
            String path = inputField.getText().trim();
            if (path.isEmpty()) {
                setStatus(status, "Please select an image first.", true); return;
            }
            File f = new File(path);
            if (!f.exists() || !f.isFile()) {
                setStatus(status, "Image does not exist: " + path, true); return;
            }
            extractBtn.setEnabled(false);
            extractBtn.setText("Extracting...");
            outArea.setText("");
            setStatus(status, "Extracting message...", false);
            new Thread(() -> {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PrintStream custom = new PrintStream(baos);
                PrintStream original = System.out;
                System.setOut(custom);
                try {
                    ExtractLSB.Extract(path);
                    System.out.flush();
                    String result = baos.toString();
                    if (result.startsWith("Message: ")) result = result.substring(9);
                    final String r = result.trim();
                    SwingUtilities.invokeLater(() -> {
                        if (r.isEmpty()) {
                            outArea.setText("No readable message found.");
                            setStatus(status, "No message detected.", true);
                        } else {
                            outArea.setText(r);
                            setStatus(status, "Message extracted successfully!", false);
                            status.setForeground(green);
                        }
                    });
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> setStatus(status, "Error: " + ex.getMessage(), true));
                } finally {
                    System.setOut(original);
                    SwingUtilities.invokeLater(() -> { extractBtn.setEnabled(true); extractBtn.setText("Extract Message"); });
                }
            }).start();
        });

        return card;
    }

    // ════════════════════════════════════════════════════════════════
    //  Component helpers
    // ════════════════════════════════════════════════════════════════

    private JLabel label(String t) {
        JLabel l = new JLabel(t);
        l.setFont(new Font("Segoe UI", Font.BOLD, 13));
        l.setForeground(text);
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }

    private JTextField textField() {
        JTextField f = new JTextField();
        f.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        f.setBackground(input);
        f.setForeground(text);
        f.setCaretColor(text);
        f.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        f.setPreferredSize(new Dimension(200, 38));
        f.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(border),
            new EmptyBorder(6, 10, 6, 10)
        ));
        return f;
    }

    private JTextArea textArea(int rows) {
        JTextArea a = new JTextArea(rows, 20);
        a.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        a.setBackground(input);
        a.setForeground(text);
        a.setCaretColor(text);
        a.setLineWrap(true);
        a.setWrapStyleWord(true);
        a.setBorder(new EmptyBorder(10, 10, 10, 10));
        return a;
    }

    private JScrollPane scroll(JTextArea area) {
        JScrollPane sp = new JScrollPane(area);
        sp.setBorder(BorderFactory.createLineBorder(border));
        sp.getViewport().setBackground(input);
        return sp;
    }

    private JPanel fieldRow(JTextField field, JButton btn) {
        JPanel row = new JPanel(new BorderLayout(8, 0));
        row.setOpaque(false);
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        row.add(field, BorderLayout.CENTER);
        row.add(btn, BorderLayout.EAST);
        return row;
    }

    private JButton accentBtn(String t) {
        JButton b = new JButton(t);
        b.setFont(new Font("Segoe UI", Font.BOLD, 15));
        b.setBackground(accent);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setBorder(new EmptyBorder(12, 32, 12, 32));
        return b;
    }

    private JButton secondaryBtn(String t) {
        JButton b = new JButton(t);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setBackground(input);
        b.setForeground(text);
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(border),
            new EmptyBorder(8, 16, 8, 16)
        ));
        return b;
    }

    // ── Helpers ─────────────────────────────────────────────────────

    private void setStatus(JLabel lbl, String msg, boolean err) {
        lbl.setText(msg);
        lbl.setForeground(err ? red : textDim);
    }

    private void pickOpen(JTextField target) {
        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            target.setText(fc.getSelectedFile().getAbsolutePath());
    }

    private void pickSave(JTextField target) {
        JFileChooser fc = new JFileChooser();
        if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String p = fc.getSelectedFile().getAbsolutePath();
            if (!p.toLowerCase().endsWith(".png")) p += ".png";
            target.setText(p);
        }
    }
}
