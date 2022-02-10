//Nikolaos Andriotis 9472 6955854088 andriotis@ece.auth.gr
//Vasileios Kyrgyridis 9617 6943059829 vasikyrg@ece.auth.gr
public class Player {
    protected int playerId;
    protected String name;
    protected int score;
    protected Board board;

    public Player() {
        this.playerId = 0;
        this.name = "";
        this.score = 0;
        this.board = new Board();
    }
    public Player(int playerId, String name, int score, int N, int M, int num_of_snakes, int num_of_ladders, int num_of_apples) {
        this.playerId = playerId;
        this.name = name;
        this.score = score;
        this.board = new Board(N, M, num_of_snakes, num_of_ladders, num_of_apples);
    }
    public Player(int playerId, String name, int score, Board board) {
        this.playerId = playerId;
        this.name = name;
        this.score = score;
        this.board = new Board(board);
    }
    public Player(Player player) {
        this.playerId = player.getPlayerId();
        this.name = player.getName();
        this.score = player.getScore();
        this.board = new Board(player.getBoard());
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void setBoard(Board board) {
        this.board = new Board(board);
    }

    public int getPlayerId() {
        return this.playerId;
    }
    public String getName() {
        return this.name;
    }
    public int getScore() {
        return this.score;
    }

    public Board getBoard() {
        return this.board;
    }

    public Integer[] move(int id, int die, boolean evaluate) {
        int snakes_beaten = 0;
        int ladders_used = 0;
        int apples_eaten = 0;
        int score = 0;
        int red_apples = 0, black_apples = 0;
        Integer[] player_progress = new Integer[]{id, snakes_beaten, ladders_used, apples_eaten, score, die, red_apples, black_apples};
        if(player_progress[0] + die >= 200) {
            player_progress[0] = 200;
            return player_progress;
        }
        player_progress[0] += die;
        for(int j=0; j < getBoard().getSnakes().length; ++j) {
            if (player_progress[0] == getBoard().getSnakes()[j].getHeadId()) {
                player_progress[0] = getBoard().getSnakes()[j].getTailId();
                if(evaluate) {
                    player_progress[1]++;
                    System.out.println(getName() + " was eaten by a snake.");
                }
                return player_progress;
            }
        }

        for(int j=0; j < getBoard().getLadders().length; ++j) {
            if (player_progress[0] == getBoard().getLadders()[j].getDownStepId() && !getBoard().getLadders()[j].getBroken()) {
                player_progress[0] = getBoard().getLadders()[j].getUpStepId();
                if(evaluate) {
                    player_progress[2]++;
                    getBoard().getLadders()[j].setBroken(true);
                    System.out.println(getName() + " used a ladder and it's now broken.");
                }
                return player_progress;
            }
        }

        for(int j=0; j < getBoard().getApples().length; ++j) {
            if (player_progress[0] == getBoard().getApples()[j].getAppleTileId() && getBoard().getApples()[j].getPoints() != 0) {
                if (getBoard().getApples()[j].getColor().equals("R")) {
                    player_progress[4] += getBoard().getApples()[j].getPoints();
                    if(evaluate) {
                        System.out.println(getName() + " ate a red apple.");
                        player_progress[3]++;
                        player_progress[6]++;
                        getBoard().getApples()[j].setPoints(0);
                    }
                    return player_progress;
                }
                else {
                    player_progress[4] += getBoard().getApples()[j].getPoints();
                    if(evaluate) {
                        System.out.println(getName() + " ate a black apple.");
                        player_progress[3]++;
                        player_progress[7]++;
                        getBoard().getApples()[j].setPoints(0);
                    }
                    return player_progress;
                }
            }
        }
        return player_progress;
    }
}