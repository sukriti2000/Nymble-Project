package Travel;

import java.util.ArrayList;
import java.util.List;
//class for Passenger
public class Passenger {
    //Name of the Passenger
    String PassengerName;
    //Package type of passenger
    String PassengerPackageType;
    //Account balance
    int PassengeAccountBal;
    //unique id no
    int PassengerNo = 0;
    static int Pinc = 0;
    //List of activities enrolled in
    List<Activity> PassengerActivity;

    {
        Pinc++;
    }
  //Constructor
    Passenger(String PassengerName, String PassengerPackageType, int PassengerAccountBal) {
        PassengerNo = Pinc;
        PassengerActivity = new ArrayList<Activity>();
        this.PassengerName = PassengerName;
        this.PassengerPackageType = PassengerPackageType;
        this.PassengeAccountBal = PassengerAccountBal;
    }
   //Function for adding activity
    public void addActivity(Activity optedActivity) {
        int cost = optedActivity.ActivityCost;
        //Subtracting the cost from account balance according to the package type of passenger
        //PackageType A has no discount
        if (PassengerPackageType.equals("A")) {
            PassengeAccountBal = PassengeAccountBal - cost;
        }
        //Packagetype B has 10% discount
        else if (PassengerPackageType.equals("B")) {
            cost = (int) (cost-(0.1*cost));
            PassengeAccountBal = PassengeAccountBal - cost;
        }

        //PackageType C can enroll for free


        optedActivity.ActivityCurrentSize++;
        PassengerActivity.add(optedActivity);
        System.out.println("passenger enrolled to " + optedActivity.ActivityName);
    }
}
