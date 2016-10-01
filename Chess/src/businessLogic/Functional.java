package businessLogic;

import java.util.ArrayList;

public class Functional {
    public static ArrayList<Integer> splitCoordinatesString(String str){
        ArrayList<Integer> coord= new ArrayList<>();
        //to set 0,0 as upper left corner
        coord.add(8-((int)str.charAt(1)-(int)'0'));//rows first
        coord.add(((int)str.charAt(0))-((int)'a'));//cols second
        return coord;
    }
    
    public static int[] splitDataPair(ArrayList<Integer> data){
        int coord[]={data.get(0),data.get(1)};
        return coord;
    }
}
