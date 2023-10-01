
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Back {

    
    
    

    

    public void sort(String path) {
        File dir = new File(path);
        Main.updateSortCompletionState(Main.INCOMPLETE);
        Main.mainFrame.repaint();
        for (File file : dir.listFiles()) {
            if (file.isFile()) {

                boolean foundDir = false;

                for (File file2 : dir.listFiles()) {
                    
                    if (file.isDirectory() && file.getName() == getFileType(file)) {
                        try {
                            Path temp = Files.move(Paths.get(file.getAbsolutePath())
                            ,Paths.get(file2.getAbsolutePath()));
                            foundDir = true;

                        if (temp != null) System.out.println("File moved!");
                        else System.out.println("File movement failed.");
                        } catch (IOException e) {e.printStackTrace();}
                        
                    }
                }

                if (!foundDir) {
                    String thePath = file.getParentFile().getAbsolutePath() + "/" + getFileType(file);
                    new File(thePath).mkdirs();
                    try {
                        Files.move(Paths.get(file.getAbsolutePath()), Paths.get(thePath + "/" + file.getName()));
                    } catch (IOException e) {e.printStackTrace();}
                }

            }
        }
        Main.updateSortCompletionState(Main.COMPLETE);
    }

    public String getFileType(File file) {
        String filename = file.getName();
        if (!stringContains(filename, '.')) return null;

        
        String[] wordList = filename.split("\\.");
        
        if (wordList.length == 0) return null;
        return wordList[wordList.length - 1];
    }

    public boolean stringContains(String str, char c) {
        for (char character : str.toCharArray()) {
            if (character == c) return true;
        }
        return false;
    }

}