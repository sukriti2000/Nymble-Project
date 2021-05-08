package Travel;
//class for Acivity
public class Activity {
    //helper variable for maintaining activity number
    static int Ainc = 0;
    //Actual variable for maintaining activity number
    int ActivityNo = 0;
    //Name of the activity
    String ActivityName;
    //Description for the activity
    String ActivityDescription;
    //Destination for the activity
    String ActivityDestination;
    //Cost of Activity
    int ActivityCost;
    //Maximum capacity of activity
    int ActivityCap;
    //Number of passengers currently enrolled
    int ActivityCurrentSize = 0;

    {
        Ainc++;
    }
    //Constructor
    Activity(String ActivityName, String ActivityDescription, int ActivityCost, int ActivityCap, String ActivityDest) {
        ActivityNo = Ainc;
        this.ActivityName = ActivityName;
        this.ActivityDescription = ActivityDescription;
        this.ActivityCost = ActivityCost;
        this.ActivityCap = ActivityCap;
        ActivityDestination = ActivityDest;
    }
}
