/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package INTEROP.FACTORIES;

import ca.uhn.hl7v2.model.AbstractMessage;

/**
 *
 * @author antoi
 */
public abstract class ADTFactory {
    public static final int v23 = 23;
    public static final int v231 = 231;
    public static final int v25 = 25;
    public static final int v251 = 251;
    
    public abstract AbstractMessage getA01();
    public abstract AbstractMessage getA28();
    public abstract AbstractMessage getA31();
    
    public static ADTFactory getADTFactory(int type){
        switch(type){
            case v23 : 
                return new ADT_v23();
            case v231 : 
                return new ADT_v231();
            case v25 : 
                return  new ADT_v25();
            case v251 : 
                return  new ADT_v251();
            
            default : 
                return null;
        }
    }
}
