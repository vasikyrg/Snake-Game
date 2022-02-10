//Nikolaos Andriotis 9472 6955854088 andriotis@ece.auth.gr
//Vasileios Kyrgyridis 9617 6943059829 vasikyrg@ece.auth.gr
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.HashMap;

public class Game {
    private int round;

    public Game() {
        this.round = 0;
    }

    public Game(int round) {
        this.round = round;
    }

    public Game(Game game) {
        this.round = game.getRound();
    }

    public int getRound() {
        return this.round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public HashMap<Integer, Integer> setTurns(ArrayList<Object> players){
        HashMap<Integer, Integer> turns = new HashMap<Integer, Integer>(players.size());
        for(int i=0; i<players.size(); i++) {
            turns.put(((Player) players.get(i)).getPlayerId(), ThreadLocalRandom.current().nextInt(1,7));
            for (int j=0; j<i; j++) {
                while(turns.get(((Player) players.get(i)).getPlayerId()) == turns.get(((Player) players.get(j)).getPlayerId()))
                    turns.put(((Player) players.get(i)).getPlayerId(), ThreadLocalRandom.current().nextInt(1, 7));
            }
        }
        return turns;
    }

    public static void main(String[] args) {

        int round = 1, dice1, P1_score=0;

        Game game = new Game(1);

        Board board1 = new Board(20, 10, 3, 3, 6);
        Player P1 = new Player(1, "Nick", 0, board1);
        HeuristicPlayer P2 = new HeuristicPlayer(2, "Bill", 0, board1, 1);

        ArrayList<Object> players = new ArrayList<>(2);

        players.add(P1);
        players.add(P2);

        HashMap<Integer, Integer> turns = new HashMap<Integer, Integer>(game.setTurns(players));

        board1.createBoard();
        board1.createElementBoard();

        System.out.println("Nick rolled the dice and it's " + turns.get(1));
        System.out.println("Bill rolled the dice and it's " + turns.get(2));

        int[] P1_array = new int[]{1, 0, 0, 0, 0, 0, 0, 0};
        int[] P2_array = new int[]{1, 0, 0, 0, 0, 0, 0, 0};

        while (P1_array[0] < 200 || P2_array[0] < 200) {
            System.out.println();
            System.out.println("Current round is: " + round);
            System.out.println();
            if (turns.get(1) > turns.get(2)) {
                dice1 = ThreadLocalRandom.current().nextInt(1, 7);
                System.out.println("Nick rolled " + dice1);
                P1_array[4] = P1.move(P1_array[0], dice1, false)[4];
                P1_array[0] = P1.move(P1_array[0], dice1, true)[0];
                P1_score += P1_array[4];
                System.out.println("Now Nick is on tile : " + P1_array[0]);
                System.out.println("Now Nick's score is : " + P1_array[4]);
                if(P1_array[0] == 200) {
                    System.out.println("Nick won!");
                    break;
                }
                P2_array[0] = P2.getNextMove(P2_array[0]);
                System.out.println("Now Bill is on tile : " + P2_array[0]);
                P2.statistics();
                System.out.println();
                if(P2_array[0] == 200) {
                    System.out.println("Bill won!");
                    break;
                }
            }
            else {
                P2_array[0] = P2.getNextMove(P2_array[0]);
                System.out.println("Now Bill is on tile : " + P2_array[0]);
                P2.statistics();
                System.out.println();
                if(P2_array[0] == 200) {
                    System.out.println("Bill won!");
                    break;
                }
                dice1 = ThreadLocalRandom.current().nextInt(1, 7);
                System.out.println("Nick rolled " + dice1);
                P1_array[4] = P1.move(P1_array[0], dice1, true)[4];
                P1_array[0] = P1.move(P1_array[0], dice1, true)[0];
                P1_score += P1_array[4];
                System.out.println("Now Nick is on tile : " + P1_array[0]);
                System.out.println("Now Nick's score is : " + P1_array[4]);
                if(P1_array[0] == 200) {
                    System.out.println("Nick won!");
                    break;
                }
            }
            round++;
        }
        System.out.println("Nick's total score is: " + P1_score);
        System.out.println("Total rounds were: " + round);
    }
}