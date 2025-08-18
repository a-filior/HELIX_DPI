/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Static;

import java.awt.Color;

/**
 *
 * @author antoi
 */
public class IHMColors {
    public static Color GLOBAL_BACKGROUND_COLOR;
    public static Color LIST_BACKGROUND_COLOR_1;
    public static Color LIST_BACKGROUND_COLOR_2;
    
    public static Color JLABEL_BUTTON_MENU_DEFAULT;
    public static Color JLABEL_BUTTON_MENU_ENTERED;
    
    public static Color JLABEL_BUTTON_MENU_CLICKED;
    public static Color JLABEL_BUTTON_MENU_SELECTED;
    
    public static Color getHexaToColor(String colorHexa){
        return new Color(Integer.parseInt(colorHexa.substring(0, 2), 16)
        ,Integer.parseInt(colorHexa.substring(2, 4), 16)
        ,Integer.parseInt(colorHexa.substring(4, 6), 16));

    }
    
    public static String getColorToHexa(Color color){
        return String.valueOf(Integer.toHexString(color.getRed()) 
        + String.valueOf(Integer.toHexString(color.getGreen()))
        + String.valueOf(Integer.toHexString(color.getBlue())));
    }    
    
    
    public static void initColors(){
        GLOBAL_BACKGROUND_COLOR = getHexaToColor("259797");
        
        LIST_BACKGROUND_COLOR_1 = getHexaToColor("6eb3b2");
        LIST_BACKGROUND_COLOR_2 = getHexaToColor("94c6c5");
        
        JLABEL_BUTTON_MENU_ENTERED = getHexaToColor("43A0A0");
        JLABEL_BUTTON_MENU_CLICKED = getHexaToColor("81BDBC");
        JLABEL_BUTTON_MENU_SELECTED = getHexaToColor("6EB3B2");
        JLABEL_BUTTON_MENU_DEFAULT = GLOBAL_BACKGROUND_COLOR;
        
    }
}
