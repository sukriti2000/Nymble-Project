package Travel;

import java.util.ArrayList;
import java.util.List;
//class for Destination
public class Destination {
    //helper variable for maintaining destination number
    static int dinc = 0;
    //actual variable for maintaining destination number
    int DestNo;
    //Name of Destination
    String DestName;
    //List for Activity
    List<Activity> DestActivities;

    {
        dinc++;
    }
  //Constructor
    Destination(String DestName) {
        DestNo = dinc;
        this.DestName = DestName;
        DestActivities = new ArrayList<Activity>();
    }
}
