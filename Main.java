
import javax.swing.*;
import java.awt.Color;
import java.io.File;

public class Main {

    public static JFrame mainFrame;
    public static String currentPath = "";
    public static JLabel dirLabel, progressLabel;
    public static JButton sortButton;
    public static int DIDNT_BEGIN = -1, INCOMPLETE = 0, COMPLETE = 1;
    private static int sortCompletionState = DIDNT_BEGIN;
  

    public static void updateSortCompletionState(int state) {
        sortCompletionState = state;
        if (sortCompletionState == DIDNT_BEGIN) progressLabel.setVisible(false);
        else if (sortCompletionState == INCOMPLETE) {
            progressLabel.setVisible(true);
            progressLabel.setText("Sorting...");
        } else {
            progressLabel.setVisible(true);
            progressLabel.setText("Files sorted succesfully.");
        }
    }

    public static void main(String[] args) {

        Back backend = new Back(); // initialize the file sorter itself

        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLayout(null);
        mainFrame.getContentPane().setBackground(Color.BLACK);
        mainFrame.setTitle("File sorter");

        dirLabel = new JLabel();
        dirLabel.setBounds(400 - 250, 150 - 25, 500, 50);
        dirLabel.setFont(MyFont.getFont(15));
        dirLabel.setForeground(Color.WHITE);
        dirLabel.setHorizontalAlignment(JLabel.CENTER);


        JButton fileDialogButton = new JButton("Select file");
        fileDialogButton.setBounds(400 - 100, 200 - 25, 200, 50);
        fileDialogButton.setBackground(new Color(20, 20, 20));
        fileDialogButton.setForeground(Color.WHITE);
        fileDialogButton.setFont(MyFont.getFont(25));
        fileDialogButton.addActionListener(e -> {
            selectFile();
        });

        sortButton = new JButton("Sort");
        sortButton.setBounds(400 - 75, 300 - 25, 150, 50);
        sortButton.setBackground(new Color(20, 20, 20));
        sortButton.setForeground(Color.WHITE);
        sortButton.setFont(MyFont.getFont(25));
        sortButton.setFocusPainted(false);
        sortButton.addActionListener(e -> {
            if (currentPath != "") {
                backend.sort(currentPath);
            }
        });
        sortButton.setVisible(false);

        progressLabel = new JLabel();
        progressLabel.setVisible(false);
        progressLabel.setBounds(400 - 250, 400 - 25, 500, 50);
        progressLabel.setFont(MyFont.getFont(14));
        progressLabel.setForeground(new Color(140, 140, 140));
        progressLabel.setHorizontalAlignment(JLabel.CENTER);


        mainFrame.add(dirLabel);
        mainFrame.add(fileDialogButton);
        mainFrame.add(sortButton);
        mainFrame.add(progressLabel);

        mainFrame.setVisible(true);

        
        
    }

    static void selectFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("Select file to sort");
        fileChooser.setCurrentDirectory(new File("C://Users/" + System.getProperty("user.name") + "/Desktop"));

        int resultCode = fileChooser.showOpenDialog(null);
        File selectedFile;

        if (resultCode == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            currentPath = selectedFile.getAbsolutePath();
            dirLabel.setText("Current directory: " + currentPath);
            sortButton.setVisible(true);
            updateSortCompletionState(DIDNT_BEGIN);
        }

    }
    

}