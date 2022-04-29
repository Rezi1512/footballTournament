public class Team implements Comparable<Team>{
   private String name;
   private int points = 0;
   private int nrOfMatch = 0;
   private int nrOfWins = 0;
   private int nrOfLooses = 0;
   private int nrOfDraws = 0;
   private int goalsConceded = 0;
   private int goalDifference = 0;
   private int goalScore = 0;

   public void setName(String name){
       this.name=name;
   }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setNrOfMatch(int nrOfMatch) {
        this.nrOfMatch = nrOfMatch;
    }

    public void setNrOfWins(int nrOfWins) {
        this.nrOfWins = nrOfWins;
    }

    public void setNrOfLooses(int nrOfLooses) {
        this.nrOfLooses = nrOfLooses;
    }

    public void setNrOfDraws(int nrOfDraws) {
        this.nrOfDraws = nrOfDraws;
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }

    public void setGoalScore(int goalScore) {
        this.goalScore = goalScore;
    }

    public void setGoalsConceded(int goalsConceded) {
        this.goalsConceded = goalsConceded;
    }

    public String getName(){
       return name;
    }

    public int getPoints() {
        return points;
    }

    public int getNrOfMatch() {
        return nrOfMatch;
    }

    public int getNrOfWins() {
        return nrOfWins;
    }

    public int getNrOfLooses() {
        return nrOfLooses;
    }

    public int getNrOfDraws() {
        return nrOfDraws;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public int getGoalScore() {
        return goalScore;
    }

    public int getGoalsConceded() {
        return goalsConceded;
    }

    @Override
    public int compareTo(Team o) {
        if (this.getPoints() == o.getPoints()){
            if (this.getNrOfWins() == o.getNrOfWins()){
                if (this.getGoalDifference() == o.getGoalDifference()){
                    if (this.getGoalScore() == o.getGoalScore()){
                        if (this.getNrOfMatch() == o.getNrOfMatch()){
                            return 0;
                        } else if (this.getNrOfMatch()>o.getNrOfMatch()){
                            return -1;
                        } else {
                            return 1;
                        }
                    } else if (this.getGoalScore()>o.getGoalScore()){
                        return -1;
                    } else {
                        return 1;
                    }
                } else if (this.getGoalDifference()>o.getGoalDifference()){
                    return -1;
                } else {
                    return 1;
                }
            } else if (this.getNrOfWins()>o.getNrOfWins()){
                return -1;
            } else {
                return 1;
            }
        } else if (this.getPoints() >o.getPoints()){
            return -1;
        } else {
            return 1;
        }


    }



}
