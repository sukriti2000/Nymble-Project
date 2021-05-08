package Travel;

import java.util.*;
import java.io.*;

//main class for Travel Package Agency
public class TravelPackage {
    //helping variable for setting Travel Package Number
    private static int inc = 0;
    //Unique Id for travel Package
    private int TravelPackageNo = 0;
    //Maximum Capacity for Passengers
    private int TravelPackageCapacity;
    //Name of the Travel Package
    private String TravelPackageName;
    //List of Destinations for Travel Package
    private List<Destination> TravalPackagedest;
    //List of Passengers opting the Package
    private List<Passenger> TravelPackagePassenger;
    //Hashmap for checking unique activities
    HashMap<String, Activity> TravelMap;

    //Instance Initialization Block
    {
        inc++;
    }

    //Constructor for Travel Package having values Name and Capacity of Passengers given by the user
    TravelPackage(String TravelPackageName, int TravelPackageCapacity) {
        TravelMap = new HashMap<String, Activity>();
        TravelPackageNo = inc;
        this.TravelPackageCapacity = TravelPackageCapacity;
        this.TravelPackageName = TravelPackageName;
        TravalPackagedest = new ArrayList<Destination>();
        TravelPackagePassenger = new ArrayList<Passenger>();
    }


    //Task1 : Print itinerary of the travel package including:
    //    1. Travel package name,
    //    2. Destinations and details of the activities available at each destination, like name, cost, capacity and description.
    void printDestinationInfo() {
        System.out.println("Travel Package name: " + TravelPackageName);
        System.out.println();
        //Printing destination list
        for (int i = 0; i < TravalPackagedest.size(); i++) {
            Destination currentDest = TravalPackagedest.get(i);
            System.out.println();
            System.out.println("Destination number " + (i + 1) + ": " + currentDest.DestName);
            //Printing activities details
            for (int j = 0; j < currentDest.DestActivities.size(); j++) {
                Activity currActivity = currentDest.DestActivities.get(j);
                System.out.println("Activity number " + (j + 1) + ": " + currActivity.ActivityName);
                System.out.println("Activity Description :" + currActivity.ActivityDescription);
                System.out.println("Activity Cost :" + currActivity.ActivityCost);
                System.out.println("Activity Capacity :" + currActivity.ActivityCap);
            }
        }

    }

    //Task2 : Print the passenger list of the travel package including:
    //    1. package name,
    //    2. passenger capacity,
    //    3. number of passengers currently enrolled and
    //    4. name and number of each passenger
    void printPassengerList() {
        //Printing Travel package details
        System.out.println("Travel package name : " + TravelPackageName);
        System.out.println("Travel package capacity : " + TravelPackageCapacity);
        System.out.println("Number of enrolled passengers : " + TravelPackagePassenger.size());
        //Printing passenger name  and number of each passenger
        for (int i = 0; i < TravelPackagePassenger.size(); i++) {
            Passenger currPass = TravelPackagePassenger.get(i);
            System.out.println(currPass.PassengerName + " " + currPass.PassengerNo);
        }
    }

    //Task3  : Print the details of an individual passenger including their
    //    1. name,
    //    2. passenger number,
    //    3. balance (if applicable),
    //    4. list of each activity they have signed up for, including the destination the at which the activity is
    //       taking place and the price the passenger paid for the activity.
    void printPassengerDetails() {
        for (int i = 0; i < TravelPackagePassenger.size(); i++) {
            Passenger currPass = TravelPackagePassenger.get(i);
            System.out.println("Passenger name : " + currPass.PassengerName);
            System.out.println("Passenger number : " + currPass.PassengerNo);

            //Printing account balance for passengers having packagetype standard or premium
            if (!currPass.PassengerPackageType.equals("C"))
                System.out.println("Passenger account balance : " + currPass.PassengeAccountBal);
            //Activities in which passenger is involved
            for (int j = 0; j < currPass.PassengerActivity.size(); j++) {
                Activity currAct = currPass.PassengerActivity.get(j);
                System.out.println("Acivity name : " + currAct.ActivityName);
                System.out.println("Acivity destination : " + currAct.ActivityDestination);
                int cost = currAct.ActivityCost;
                if (currPass.PassengerPackageType == "B")
                    cost = cost - (10 / 100 * cost);
                else if (currPass.PassengerPackageType == "C")
                    cost = 0;

                System.out.println(cost);
            }
        }

    }

    //Print the details of all the activities that still have spaces available, including how many spaces are available.
    void printActivityAvailability() {
        Iterator TravelMapElement = TravelMap.entrySet().iterator();
        while (TravelMapElement.hasNext()) {
            Map.Entry mapElement = (Map.Entry) TravelMapElement.next();
            String key = (String) mapElement.getKey();
            Activity actElement = TravelMap.get(key);
            //checking the space availability
            if (actElement.ActivityCurrentSize < actElement.ActivityCap)
                System.out.println("Activity name :" + " " + key + "      Available seats: " + (actElement.ActivityCap - actElement.ActivityCurrentSize) + "        Activity cost:  " + actElement.ActivityCost + "       Activity description : " + actElement.ActivityDescription + "         Activity Destination :" + actElement.ActivityDestination);
        }
    }

    //function for add activities in a particular destination
    void addActivities(Destination DestObject, int Dactivity) throws IOException {
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader s = new BufferedReader(r);
        for (int k = 0; k < Dactivity; k++) {
            System.out.print("Enter name for activity number " + (k + 1) + ":");
            String Aname = s.readLine();
            //checking if activity already exists oor not
            if (TravelMap.containsKey(Aname)) {
                System.out.println("Activity already at a destination,please enter different activity.");
                k--;
                continue;
            }
            //Taking input for activity details
            System.out.println("Enter Activity description: ");
            String Adescription = s.readLine();
            System.out.println("Enter Activity cost: ");
            int Acost = Integer.parseInt(s.readLine());
            System.out.println("Enter Activity capacity: ");
            int Acapacity = Integer.parseInt(s.readLine());
            Activity ActivityObject = new Activity(Aname, Adescription, Acost, Acapacity, DestObject.DestName);
            TravelMap.put(Aname, ActivityObject);
            DestObject.DestActivities.add(ActivityObject);

        }
    }

    //function for adding destination to a particular travel package
    void addDestinations(int Tdest) throws IOException {
        for (int j = 0; j < Tdest; j++) {
            InputStreamReader r = new InputStreamReader(System.in);
            BufferedReader s = new BufferedReader(r);
            System.out.println("Enter the destination name " + (j + 1) + " :");
            String Dname = s.readLine();
            System.out.println("Enter the no of activities: ");
            int Dactivity = Integer.parseInt(s.readLine());
            Destination DestObject = new Destination(Dname);
            addActivities(DestObject, Dactivity);
            TravalPackagedest.add(DestObject);

        }
    }

    public static void main(String[] args) throws Exception {
        //List containing all the travel packages
        List<TravelPackage> TravelList = new ArrayList<TravelPackage>();
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader s = new BufferedReader(r);
        System.out.println("Define a package");
        //boolean variable for a choice to define a new package
        boolean definePackage = true;
        do {
            //Taking input for a new package details
            System.out.println("Enter package name: ");
            String Tname = s.readLine();
            System.out.println("Enter passenger capacity: ");
            int Tcapacity = Integer.parseInt(s.readLine());
            System.out.println("Enter Number of destination: ");
            int Tdest = Integer.parseInt(s.readLine());
            //Making a new object for travel package
            TravelPackage TravelObject = new TravelPackage(Tname, Tcapacity);
            //Calling function for adding destinations to the travel package
            TravelObject.addDestinations(Tdest);

            //boolean for choice to add passengers
            boolean addPass = true;
            int PassSize = 0;
            // Adding passenger if boolean is true and travel package has empty spaces
            while (addPass == true && PassSize < Tcapacity) {
                System.out.println("Enter YES if you want to add Passenger and NO if you don't want to add passenger :");
                String ifAdd = s.readLine();
                if (ifAdd.equals("NO") || ifAdd.equals("no") || ifAdd.equals("No")) {
                    addPass = false;
                    break;
                }
                //Adding passenger details
                System.out.println("Enter passenger name: ");
                String PName = s.readLine();

                //Taking input for the packageType of passenger
                System.out.println("passenger's package type: Enter A : Standard,  B : Premium,  C : Gold");
                String Ppackagetype = s.readLine();
                while (!Ppackagetype.equals("A") && !Ppackagetype.equals("B") && !Ppackagetype.equals("C")) {
                    System.out.println("Enter valid input");
                    Ppackagetype = s.readLine();
                }
                int Pbalance = 0;
                //If package type is C then no criteria for the account balance
                if (!Ppackagetype.equals("C")) {
                    System.out.println("Enter passenger's account balance: ");
                    Pbalance = Integer.parseInt(s.readLine());
                }
                //Initializing a passenger object
                Passenger PassangerObject = new Passenger(PName, Ppackagetype, Pbalance);
                System.out.println("Enter Yes if you want to enroll for activities and No if you don't want to ");
                String ifActivity = s.readLine();
                if (ifActivity.equals("Yes") || ifActivity.equals("yes")) {
                    // boolean for choice for adding activities to the passsenger's activityList
                    boolean addAct = true;
                    do {
                        //Showing different activities available
                        System.out.println("Activity available :");
                        Iterator TravelMapElement = TravelObject.TravelMap.entrySet().iterator();
                        while (TravelMapElement.hasNext()) {
                            Map.Entry mapElement = (Map.Entry) TravelMapElement.next();
                            String key = (String) mapElement.getKey();
                            Activity actElement = TravelObject.TravelMap.get(key);
                            System.out.println("Activity name :" + " " + key + "     cost:  " + actElement.ActivityCost + "    occupied seats: " + actElement.ActivityCurrentSize);
                        }
                        System.out.println("");
                        System.out.print("Enter activity name you want to enroll :");
                        String OptActivity = s.readLine();
                        Activity CheckActivity = TravelObject.TravelMap.get(OptActivity);
                        int CheckCost = CheckActivity.ActivityCost;
                        //Checking the balance of passenger so that he/she can opt for the activity or not
                        if (CheckActivity.ActivityCurrentSize >= CheckActivity.ActivityCap) {
                            System.out.println("No seats available");
                        } else if (!PassangerObject.PassengerPackageType.equals("C") && (PassangerObject.PassengeAccountBal - CheckCost < 0)) {
                            System.out.println("No sufficient balance.");
                        } else
                            PassangerObject.addActivity(CheckActivity);
                        //Option for adding another activity
                        System.out.println("Want to add another activity?....Enter Yes to continue AND No to stop");
                        String IfEnroll = s.readLine();
                        if (IfEnroll.equals("Yes") || IfEnroll.equals("yes"))
                            addAct = true;
                        else
                            addAct = false;
                    }
                      while (addAct);

                }

                TravelObject.TravelPackagePassenger.add(PassangerObject);
                PassSize++;
            }
            // Can Add more passenger if travel package have seats available for the passenger
            if (PassSize == Tcapacity)
                System.out.println("you can not add more passangers");
            TravelList.add(TravelObject);
            TravelObject.printActivityAvailability();
            TravelObject.printDestinationInfo();
            TravelObject.printPassengerList();
            TravelObject.printPassengerDetails();
            System.out.println("Want to define a new package ? enter Y  to continue And N  to end");
            String ifDefinePack = s.readLine();
            if (ifDefinePack.equals("N"))
                definePackage = false;

        } while (definePackage == true);


    }
}


