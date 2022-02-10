//Nikolaos Andriotis 9472 6955854088 andriotis@ece.auth.gr
//Vasileios Kyrgyridis 9617 6943059829 vasikyrg@ece.auth.gr
import java.util.ArrayList;

public class HeuristicPlayer extends Player {
    public ArrayList<Integer[]> path;

    public HeuristicPlayer() {
        this.path = new ArrayList<Integer[]>();
    }
    public HeuristicPlayer(int playerId, String name, int score, Board board, int size_of_array) {
        super(playerId, name, score, board);
            this.path = new ArrayList<Integer[]>(size_of_array);
    }
    public HeuristicPlayer(Player player, int size_of_array) {
        super(player);
        this.path = new ArrayList<Integer[]>(size_of_array);
    }

    public double evaluate(int currentPos, int dice) {
        Integer[] evalMove = new Integer[2];
        evalMove[0] = move(currentPos, dice, false)[0];
        evalMove[1] = move(currentPos, dice, false)[4];
        int steps = evalMove[0] - currentPos;
        int gainPoints = evalMove[1];
        return (double)steps * 0.65 + (double)gainPoints * 0.35;
    }

    public int getNextMove(int currentPos) {
        int num = 1;
        ArrayList<Double> array_moves = new ArrayList<>(6);
        for(int i=0; i<6; i++) {
                array_moves.add(evaluate(currentPos, i+1));
        }
        double max = array_moves.get(0);
        for(int i=0; i<array_moves.size(); i++){
            if(max < array_moves.get(i)) {
                max = array_moves.get(i);
                num = i + 1;
            }
        }
        path.add(move(currentPos, num, true));
        return path.get(path.size()-1)[0];
    }

    public  void statistics() {
        int total_num_of_snakes=0, total_num_of_ladders=0, total_num_of_ra=0, total_num_of_ba=0;
        if(path.get(path.size()-1)[0] == 200) {
            System.out.println( getName() + " in round " + path.size() + " chose the dice to be " + path.get(path.size()-1)[5]);
            for(int i=0; i<path.size(); i++) {
                total_num_of_snakes += path.get(i)[1];
                total_num_of_ladders += path.get(i)[2];
                total_num_of_ra += path.get(i)[6];
                total_num_of_ba += path.get(i)[7];

            }
            System.out.println("The total number of snakes, " + getName() + " was beaten by is " + total_num_of_snakes);
            System.out.println("The total number of ladders, "  + getName() + " used is " + total_num_of_ladders);
            System.out.println("The total number of red apples, "  + getName() + " ate is " + total_num_of_ra);
            System.out.println("The total number of black apples, "  + getName() + " ate is " + total_num_of_ba);
        }
        else {
            System.out.print( getName() + " in round " + path.size() + " chose the dice to be " + path.get(path.size()-1)[5]);
            if( path.get(path.size()-1)[1] != 0)
                System.out.println(" and was beaten by a snake.");
            if(path.get(path.size()-1)[2] != 0)
                System.out.println(" and used a ladder.");
            if(path.get(path.size()-1)[6] != 0)
                System.out.println(" and ate a red apple.");
            if(path.get(path.size()-1)[7] != 0)
                System.out.println( " and ate a black apple.");
        }
    }
}