import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class MyFont {
    
    public static Font defaultFont;
    

    public static Font getFont(float size){
        
        defaultFont = new Font("sans serif", Font.BOLD, (int)size);

        try{
            return Font.createFont(Font.TRUETYPE_FONT, new File("MyFont.ttf")).deriveFont(size);
        }catch (IOException e){
            e.printStackTrace();
        }catch (FontFormatException e){
            e.printStackTrace();
        }

        return defaultFont;
        
        
        
    }
}
