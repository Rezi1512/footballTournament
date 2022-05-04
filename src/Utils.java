import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {

    public static void takeInputsFromUser(String competitionName,ArrayList<String> results,
                                          ArrayList<Team> objects) throws Exception {

        Scanner input = new Scanner(System.in);

        int nr = input.nextInt();
        if (nr < 0 || nr > 1000) {
            throw new Exception("input i papershtatshem");
        }

        System.out.println("Jepni emrin e kompeticionit");
        Scanner input1 = new Scanner(System.in);
        competitionName = input1.nextLine();
        if (competitionName == null || competitionName.length() > 100) {
            throw new Exception("emri i kompeticionit jo i pershtatshem");
        }

        System.out.println("Jepni numrin e ekipeve");
        int numberOfTeams = input.nextInt();
        if (numberOfTeams <= 1 || numberOfTeams > 30) {
            throw new Exception("numer i papershtatshem ekipesh");
        }

        ArrayList<String> teams = new ArrayList();
        for (int i = 0; i < numberOfTeams; i++) {
            System.out.println("Jepni emrin e ekipit");
            String team = input.next();
            if (team.contains("@") || team.contains("#")) {
                throw new Exception("emri i ekipit i papershtatshem");
            } else {
                teams.add(team);
            }
        }

        for (int i=0;i<teams.size();i++){
            Team  team =new Team();
            team.setName(teams.get(i));
            objects.add(team);
        }

        System.out.println("Jepni numrin e ndeshjeve");
        int numberOfMatchesPlayed = input.nextInt();
        if (numberOfMatchesPlayed < 0 || numberOfMatchesPlayed > 1000) {
            throw new Exception("numri i ndeshjeve i papershtatshem");
        }

        for (int i = 0; i < numberOfMatchesPlayed; i++) {
            System.out.println("Jepni rezultatin e ndeshjeve");
            String a = input.next();
            if (Utils.checkIfResultInputsAreOk(a)) {
                results.add(a);
            } else {
                throw new Exception("Result is invalid");
            }
        }
    }


    public static boolean checkIfResultInputsAreOk(String result) {
        String goal1 = find1Goals(result);
        String goal2 = find2Goal(result);
        return (checkIfGoalsAreOk(goal1) && checkIfGoalsAreOk(goal2));
    }


    public static boolean checkIfGoalsAreOk(String goal) {
        int goals = Integer.parseInt(goal);
        return  (goals >= 0 && goals < 20);
    }


    public static String find1Goals(String result) {
        int beginIndex = 0;
        int endIndex = 1;
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '#') {
                beginIndex = i + 1;
                break;
            }
        }
        for (int i = beginIndex; i < result.length(); i++) {
            if (result.charAt(i) == '@') {
                endIndex = i;
            }
        }
        String goal1 = result.substring(beginIndex, endIndex);
        return goal1;
    }


    public static String find2Goal(String result) {
        int beginIndex = 0;
        int endIndex = 1;
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '@') {
                beginIndex = i + 1;
            }
        }
        for (int i = beginIndex; i < result.length(); i++) {
            if (result.charAt(i) == '#') {
                endIndex = i;
            }
        }
        String goal2 = result.substring(beginIndex, endIndex);
        return goal2;
    }


    public static String findWichTeamHasWon(String result) {
        String team1 = "";
        String team2 = "";
        int beginIndex = 0;
        int endIndex = 1;
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '#') {
                endIndex = i;
                break;
            }
        }
        team1 = result.substring(beginIndex, endIndex);

        for (int i = endIndex + 1; i < result.length(); i++) {
            if (result.charAt(i) == '#') {
                beginIndex = i + 1;
            }
        }
        team2 = result.substring(beginIndex);

        if (Integer.parseInt(find1Goals(result)) > Integer.parseInt(find2Goal(result))) {
            return team1;
        } else {
            return team2;
        }
    }


    public static boolean areTeamsEqual(String result) {
        return  (Integer.parseInt(find1Goals(result)) == Integer.parseInt(find2Goal(result)));
    }


    public static String find1Team(String result) {
        int beginIndex = 0;
        int endIndex = 1;
        String firstTeam = "";
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '#') {
                endIndex = i;
                break;
            }
        }
        firstTeam = result.substring(beginIndex, endIndex);
        return firstTeam;
    }


    public static String find2Team(String result) {
        int beginIndex = 0;
        String secondTeam = "";
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '@') {
                beginIndex = i;
                break;
            }
        }
        for (int i = beginIndex; i < result.length(); i++) {
            if (result.charAt(i) == '#') {
                beginIndex = i + 1;
            }
        }
        secondTeam = result.substring(beginIndex);
        return secondTeam;
    }


    public static void putResultsOnTeams(List<String> results, List<Team> objects) {
        for (int i = 0; i < results.size(); i++) {
            String result = results.get(i);


            String firstTeam = find1Team(result);
            String secondTeam = find2Team(result);


            if (areTeamsEqual(result)) {
                int goals = Integer.parseInt(find1Goals(result));
                for (int j = 0; j < objects.size(); j++) {
                    if (objects.get(j).getName().equals(firstTeam) || objects.get(j).getName().equals(secondTeam)) {
                        objects.get(j).setPoints(objects.get(j).getPoints() + 1);
                        objects.get(j).setGoalScore(objects.get(j).getGoalScore() + goals);
                        objects.get(j).setGoalsConceded(objects.get(j).getGoalsConceded() + goals);
                        objects.get(j).setNrOfMatch(objects.get(j).getNrOfMatch() + 1);
                        objects.get(j).setNrOfDraws(objects.get(j).getNrOfDraws() + 1);
                        objects.get(j).setGoalDifference(objects.get(j).getGoalDifference() + (goals - goals));
                    }
                }
            } else {
                if (findWichTeamHasWon(result).equals(firstTeam)) {
                    String winingTeam = firstTeam;
                    String losingTeam = secondTeam;
                    int goalOfFirstTeam = Integer.parseInt(find1Goals(result));
                    int goalOfSecondTeam = Integer.parseInt(find2Goal(result));

                    for (int n = 0; n < objects.size(); n++) {
                        if (objects.get(n).getName().equals(winingTeam)) {
                            objects.get(n).setPoints(objects.get(n).getPoints() + 3);
                            objects.get(n).setGoalScore(objects.get(n).getGoalScore() + goalOfFirstTeam);
                            objects.get(n).setGoalsConceded(objects.get(n).getGoalsConceded() + goalOfSecondTeam);
                            objects.get(n).setNrOfMatch(objects.get(n).getNrOfMatch() + 1);
                            objects.get(n).setNrOfWins(objects.get(n).getNrOfWins() + 1);
                            objects.get(n).setGoalDifference(objects.get(n).getGoalDifference() + (goalOfFirstTeam - goalOfSecondTeam));

                        }
                    }
                    for (int a = 0; a < objects.size(); a++) {
                        if (objects.get(a).getName().equals(losingTeam)) {
                            objects.get(a).setPoints(objects.get(a).getPoints() + 0);
                            objects.get(a).setGoalScore(objects.get(a).getGoalScore() + goalOfSecondTeam);
                            objects.get(a).setGoalsConceded(objects.get(a).getGoalsConceded() + goalOfFirstTeam);
                            objects.get(a).setNrOfMatch(objects.get(a).getNrOfMatch() + 1);
                            objects.get(a).setNrOfLooses(objects.get(a).getNrOfLooses() + 1);
                            objects.get(a).setGoalDifference(objects.get(a).getGoalDifference() + (goalOfSecondTeam - goalOfFirstTeam));

                        }
                    }
                } else {
                    String winingTeam = secondTeam;
                    String losingTeam = firstTeam;
                    int goalOfFirstTeam = Integer.parseInt(find1Goals(result));
                    int goalOfSecondTeam = Integer.parseInt(find2Goal(result));

                    for (int b = 0; b < objects.size(); b++) {
                        if (objects.get(b).getName().equals(winingTeam)) {
                            objects.get(b).setPoints(objects.get(b).getPoints() + 3);
                            objects.get(b).setGoalScore(objects.get(b).getGoalScore() + goalOfSecondTeam);
                            objects.get(b).setGoalsConceded(objects.get(b).getGoalsConceded() + goalOfFirstTeam);
                            objects.get(b).setNrOfMatch(objects.get(b).getNrOfMatch() + 1);
                            objects.get(b).setNrOfWins(objects.get(b).getNrOfWins() + 1);
                            objects.get(b).setGoalDifference(objects.get(b).getGoalDifference() + (goalOfSecondTeam - goalOfFirstTeam));
                        }
                    }
                    for (int c = 0; c < objects.size(); c++) {
                        if (objects.get(c).getName().equals(losingTeam)) {
                            objects.get(c).setPoints(objects.get(c).getPoints() + 0);
                            objects.get(c).setGoalScore(objects.get(c).getGoalScore() + goalOfFirstTeam);
                            objects.get(c).setGoalsConceded(objects.get(c).getGoalsConceded() + goalOfSecondTeam);
                            objects.get(c).setNrOfMatch(objects.get(c).getNrOfMatch() + 1);
                            objects.get(c).setNrOfLooses(objects.get(c).getNrOfLooses() + 1);
                            objects.get(c).setGoalDifference(objects.get(c).getGoalDifference() + (goalOfFirstTeam - goalOfSecondTeam));

                        }
                    }
                }
            }
        }

    }


    public static void displayRanking(List<Team> objects, String name){
        System.out.println(name);
        for (int i=0;i< objects.size();i++){
            System.out.println((i+1)+") "+objects.get(i).getName()+" "+objects.get(i).getPoints()+"p,"+objects.get(i).getNrOfMatch()+"g ("
                    +objects.get(i).getNrOfWins()+"-"+objects.get(i).getNrOfDraws()+"-"
                    +objects.get(i).getNrOfLooses()+"), "+ objects.get(i).getGoalDifference()+"gd ("
                    +objects.get(i).getGoalScore()+"-"+objects.get(i).getGoalsConceded()+")");
        }
    }


}



