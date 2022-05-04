import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

            String competitionName="";
            ArrayList<String> results = new ArrayList();
            ArrayList<Team> objects = new ArrayList();

            Utils.takeInputsFromUser(competitionName,results,objects);
            Utils.putResultsOnTeams(results,objects);
            Collections.sort(objects);
            Utils.displayRanking(objects,competitionName);
    }
}

