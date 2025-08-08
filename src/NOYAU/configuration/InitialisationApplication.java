/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NOYAU.configuration;

import INTEROP.HL7Receiver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author antoi
 */
public class InitialisationApplication {
    
    public static Map<String, ParametrageInterop> paramOUT = new HashMap<String, ParametrageInterop>();
    
    public static void initialiserLesApplications(){
        ParametrageInterop.getLesParam().forEach(pi -> {
            if(pi.getSens() == 1){
                switch(pi.getApplication().getAppCode()){
                    case "552" : 
                        HL7Receiver receiverADT = new HL7Receiver(pi.getPort());

                        receiverADT.lancerEcoute();
                    default :
                }
            } else {
//                System.out.println("Param : " + pi.getApplication().getAppCode());
                paramOUT.put(pi.getApplication().getAppCode(), pi);
            }
        });
    }
}
