/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package INTEROP.FACTORIES;

import ca.uhn.hl7v2.model.AbstractMessage;
import ca.uhn.hl7v2.model.v23.message.*;

/**
 *
 * @author antoi
 */
public class ADT_v23 extends ADTFactory {

    @Override
    public ADT_A01 getA01() {
        System.out.println("A01 : v2.3");
        return new ADT_A01();
    }

    @Override
    public ADT_A28 getA28() {
        System.out.println("A28 : v2.3");
        return new ADT_A28();
    }

    @Override
    public ADT_A31 getA31() {
        System.out.println("A31 : v2.3");
        return new ADT_A31();
    }
    
}
