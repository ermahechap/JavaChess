package businessLogic;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Functional {

    public static ArrayList<Integer> splitCoordinatesString(String str) {
        ArrayList<Integer> coord = new ArrayList<>();
        //to set 0,0 as upper left corner
        coord.add(8 - ((int) str.charAt(1) - (int) '0'));//rows second
        coord.add(((int) str.charAt(0)) - ((int) 'a'));//cols first
        return coord;
    }

    public static int[] splitDataPair(ArrayList<Integer> data) {
        int coord[] = {data.get(0), data.get(1)};
        return coord;
    }

    public static char toCharCoordinate(int cr) {
        return (char) (cr + ((int) 'a'));
    }

    public static ArrayList<Integer> reverseCoordinade(ArrayList<Integer> coord) {
        ArrayList<Integer> reverse = new ArrayList<>();
        //to set 0,0 as upper left corner
        reverse.add(coord.get(0) + (int) 'a');
        reverse.add(coord.get(1) + (int) '0');
        return reverse;
    }

    static public Object deepCopy(Object oldObj) {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            ByteArrayOutputStream bos
                    = new ByteArrayOutputStream(); // A
            oos = new ObjectOutputStream(bos); // B
            // serialize and pass the object
            oos.writeObject(oldObj);   // C
            oos.flush();               // D
            ByteArrayInputStream bin
                    = new ByteArrayInputStream(bos.toByteArray()); // E
            ois = new ObjectInputStream(bin);                  // F
            // return the new object
            return ois.readObject(); // G
        } catch (Exception e) {
            System.out.println("Exception in ObjectCloner = " + e);
            return null;
        } finally {
            try {
                oos.close();
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(Functional.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
