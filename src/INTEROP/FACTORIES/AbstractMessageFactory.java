/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package INTEROP.FACTORIES;

/**
 *
 * @author antoi
 */
public abstract class AbstractMessageFactory {
    public static final int ADT = 1;
    public static final int ORU = 2;
    public static final int SIU = 3;
    
    

    public static AbstractMessageFactory getFactory(int type){
        switch(type){
            case ADT : 
                return new HL7FactoryADT();
            case ORU : 
                return  new HL7FactoryORU();
            case SIU : 
                return  new HL7FactorySIU();
            
            default : 
                return null;
        }
    }

}
