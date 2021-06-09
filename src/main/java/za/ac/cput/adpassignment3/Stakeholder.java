/**
 * Stakeholder.java
 * @author Mogammad Faeedh Daniels
 * Student number: 219174288
 */
package za.ac.cput.adpassignment3;

import java.io.Serializable;

public class Stakeholder implements Serializable {
    private String stHolderId;

    public Stakeholder() {
    }
    
    public Stakeholder(String stHolderId) {
        this.stHolderId = stHolderId;
    }
    
    public String getStHolderId() {
        return stHolderId;
    }

    public void setStHolderId(String stHolderId) {
        this.stHolderId = stHolderId;
    }

    @Override
    public String toString() {
       return stHolderId;
    }

}
