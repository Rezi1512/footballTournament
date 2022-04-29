import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

            Scanner input = new Scanner(System.in);

            int nr = input.nextInt();
            if (nr < 0 || nr > 1000) {
                throw new Exception("invalid input");
            }

            System.out.println("Jepni emrin e kompeticionit");
            Scanner input1 = new Scanner(System.in);
            String competitionName = input1.nextLine();
            if (competitionName == null || competitionName.length() > 100) {
                throw new Exception("invalid competition name");
            }

            System.out.println("Jepni numrin e ekipeve");
            int numberOfTeams = input.nextInt();
            if (numberOfTeams <= 1 && numberOfTeams > 30) {
                throw new Exception("invalid number of teams");
            }

            ArrayList<String> teams = new ArrayList();
            for (int i = 0; i < numberOfTeams; i++) {
                System.out.println("Jepni emrat e ekipeve");
                String team = input.next();
                if (team.contains("@") || team.contains("#")) {
                    throw new Exception("invalid team name");
                } else {
                    teams.add(team);
                }
            }

            ArrayList<Team> objects = new ArrayList<>();
            for (int i=0;i<teams.size();i++){
                Team  team =new Team();
                team.setName(teams.get(i));
                objects.add(team);
            }

            System.out.println("Jepni numrin e ndeshjeve");
            int numberOfMatchedPlayed = input.nextInt();
            if (numberOfMatchedPlayed < 0 || numberOfMatchedPlayed > 1000) {
                throw new Exception("invalid number of match");
            }

            ArrayList<String> results = new ArrayList();
            for (int i = 0; i < numberOfMatchedPlayed; i++) {
                System.out.println("Jepni rezultatin e ndeshjeve");
                String a = input.next();
                if (Utils.checkIfResultInputsAreOk(a)) {
                    results.add(a);
                } else {
                    throw new Exception("Result is invalid");
                }
            }

            Utils.putResultsOnTeams(results,objects);
            Collections.sort(objects);
            Utils.displayRaking(objects,competitionName);


            /*for (int i=0;i< objects.size();i++){
                System.out.println((i+1)+") "+objects.get(i).getName()+" "+objects.get(i).getPoints()+"p,"+objects.get(i).getNrOfMatch()+"g ("
                                  +objects.get(i).getNrOfWins()+"-"+objects.get(i).getNrOfDraws()+"-"
                                  +objects.get(i).getNrOfLooses()+"), "+ objects.get(i).getGoalDifference()+"gd ("
                                  +objects.get(i).getGoalScore()+"-"+objects.get(i).getGoalsConceded()+")");
            }*/



    }




}

