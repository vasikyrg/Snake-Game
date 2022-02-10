//Nikolaos Andriotis 9472 6955854088 andriotis@ece.auth.gr
//Vasileios Kyrgyridis 9617 6943059829 vasikyrg@ece.auth.gr
import java.util.concurrent.ThreadLocalRandom;

public class Board {

    private int N;
    private int M;

    private int[][] tiles;
    private Snake[] snakes;
    private Ladder[] ladders;
    private Apple[] apples;

    public Board() {
        N = 0;
        M = 0;
        tiles = new int[1][1];
        tiles[0][0] = 0;
        snakes = new Snake[1];
        snakes[0] = new Snake();
        ladders = new Ladder[1];
        ladders[0] = new Ladder();
        apples = new Apple[1];
        apples[0] = new Apple();
    }
    public Board(int N, int M, int num_of_snakes, int num_of_ladders, int num_of_apples) {
        this.N = N;
        this.M = M;
        tiles = new int[N][M];

        snakes = new Snake[num_of_snakes];
        for (int i = 0; i < num_of_snakes; i++)
            snakes[i] = new Snake();


        ladders = new Ladder[num_of_ladders];
        for (int i = 0; i < num_of_ladders; i++)
            ladders[i] = new Ladder();

        apples = new Apple[num_of_apples];
        for (int i = 0; i < num_of_apples; i++)
            apples[i] = new Apple();
    }

    public Board(Board board) {
        this.N = board.getN();
        this.M = board.getM();
        tiles = new int[board.getN()][board.getM()];
        snakes = new Snake[board.getSnakes().length];
        ladders = new Ladder[board.getLadders().length];
        apples = new Apple[board.getApples().length];

        for (int i = 0; i < board.getN(); i++)
            for (int j = 0; j < board.getM(); j++)
                this.tiles[i][j] = board.getTiles()[i][j];

        for (int i = 0; i < board.getSnakes().length; i++) {
            snakes[i] = new Snake();
            this.snakes[i] = board.getSnakes()[i];
        }

        for (int i = 0; i < board.getLadders().length; i++) {
            ladders[i] = new Ladder();
            this.ladders[i] = board.getLadders()[i];
        }

        for (int i = 0; i < board.getApples().length; i++) {
            apples[i] = new Apple();
            this.apples[i] = board.getApples()[i];
        }
    }

    public void createBoard() {
        ////////////////////// TILE NAMING ///////////////////////////////

        int number_of_tile = 1;
        if(getN()%2 == 0) {
            for(int i=getN()-1; i>=0; i--) {
                if (i % 2 != 0) {
                    for (int j = 0; j < getM(); j++) {
                        getTiles()[i][j] = number_of_tile;
                        number_of_tile++;
                    }
                }
                if (i % 2 == 0) {
                    for (int j = getM() - 1; j >= 0; j--) {
                        getTiles()[i][j] = number_of_tile;
                        number_of_tile++;
                    }
                }
            }
        }
        if(getN()%2 != 0) {
            for(int i=getN()-1; i>=0; i--) {
                if(i % 2 == 0) {
                    for (int j = 0; j < getM(); j++) {
                        getTiles()[i][j] = number_of_tile;
                        number_of_tile++;
                    }
                }
                if(i % 2 != 0) {
                    for(int j =getM()-1; j>=0; j--) {
                        getTiles()[i][j] = number_of_tile;
                        number_of_tile++;
                    }
                }
            }
        }

        ///////////////////// TILE NAMING ////////////////////////////////

        //////////////////// SNAKE GENERATOR /////////////////////////////

        int snake_row_tail, snake_col_tail, snake_row_head, snake_col_head;

        for (int i = 0; i < snakes.length; i++) {
            snakes[i].setSnakeId(i);

            snake_row_tail = ThreadLocalRandom.current().nextInt(1, N);
            snake_col_tail = ThreadLocalRandom.current().nextInt(0, M);

            snakes[i].setTailId(tiles[snake_row_tail][snake_col_tail]);

            while (isDuplicateTail(snakes, i)) {
                snake_row_tail = ThreadLocalRandom.current().nextInt(1, N);
                snake_col_tail = ThreadLocalRandom.current().nextInt(0, M);

                snakes[i].setTailId(tiles[snake_row_tail][snake_col_tail]);
            }

            snake_row_head = ThreadLocalRandom.current().nextInt(0, snake_row_tail);
            snake_col_head = ThreadLocalRandom.current().nextInt(0, M-1);         // den ginetai na yparxei kefali fidiou sto telos

            snakes[i].setHeadId(tiles[snake_row_head][snake_col_head]);

            while (isDuplicateHead(snakes, i)) {
                snake_row_head = ThreadLocalRandom.current().nextInt(0, snake_row_tail);
                snake_col_head = ThreadLocalRandom.current().nextInt(0, M-1);    // den ginetai na yparxei kefali fidiou sto telos

                snakes[i].setHeadId(tiles[snake_row_head][snake_col_head]);
            }
        }

        ///////////////// SNAKE GENERATOR ////////////////////////////////

        ///////////////// LADDER GENERATOR ////////////////////////////////

        int ladder_row_down, ladder_col_down, ladder_row_up, ladder_col_up;

        for (int i = 0; i < ladders.length; i++) {
            ladders[i].setLadderId(i);

            ladder_row_down = ThreadLocalRandom.current().nextInt(1, N);
            ladder_col_down = ThreadLocalRandom.current().nextInt(0, M);

            ladders[i].setDownStepId(tiles[ladder_row_down][ladder_col_down]);

            while (isDuplicateDown(ladders, i)) {
                ladder_row_down = ThreadLocalRandom.current().nextInt(1, N);
                ladder_col_down = ThreadLocalRandom.current().nextInt(0, M);

                ladders[i].setDownStepId(tiles[ladder_row_down][ladder_col_down]);
            }

            ladder_row_up = ThreadLocalRandom.current().nextInt(0, ladder_row_down);
            ladder_col_up = ThreadLocalRandom.current().nextInt(0, M);

            ladders[i].setUpStepId(tiles[ladder_row_up][ladder_col_up]);

            while (isDuplicateUp(ladders, i)) {
                ladder_row_up = ThreadLocalRandom.current().nextInt(0, ladder_row_down);
                ladder_col_up = ThreadLocalRandom.current().nextInt(0, M);

                ladders[i].setUpStepId(tiles[ladder_row_up][ladder_col_up]);
            }
        }

        ///////////////// LADDER GENERATOR ///////////////////////////////

        ///////////////// APPLE GENERATOR ////////////////////////////////

        int apple_x, apple_y, random_apple_choice;

        for (int i = 0; i < apples.length; i++) {
            apples[i].setAppleId(i);

            apple_x = ThreadLocalRandom.current().nextInt(0, N);
            apple_y = ThreadLocalRandom.current().nextInt(0, M);

            apples[i].setAppleTileId(tiles[apple_x][apple_y]);

            while (isDuplicateApples(apples, i)) {
                apple_x = ThreadLocalRandom.current().nextInt(0, N);
                apple_y = ThreadLocalRandom.current().nextInt(0, M);

                apples[i].setAppleTileId(tiles[apple_x][apple_y]);
            }

            random_apple_choice = ThreadLocalRandom.current().nextInt(-20, 21);
            while(random_apple_choice == 0) {
                random_apple_choice = ThreadLocalRandom.current().nextInt(-20, 21);
            }
            if (random_apple_choice > 0) {
                apples[i].setColor("R");
                apples[i].setPoints(random_apple_choice);
            }
            else {
                apples[i].setColor("B");
                apples[i].setPoints(random_apple_choice);
            }
        }

        ///////////////// APPLE GENERATOR ////////////////////////////////
    }
    public void createElementBoard() {

        String[][] elementBoardSnakes = new String[N][M];
        String[][] elementBoardLadders = new String[N][M];
        String[][] elementBoardApples = new String[N][M];

        for (int i=0; i < N; i++) {
            for (int j=0; j < M; j++) {
                for (int index=0; index < getSnakes().length; index++) {
                    if (tiles[i][j] == snakes[index].getTailId()) {
                        elementBoardSnakes[i][j] = "ST" + snakes[index].getSnakeId();
                        break;
                    }
                    if (tiles[i][j] == snakes[index].getHeadId()) {
                        elementBoardSnakes[i][j] = "SH" + snakes[index].getSnakeId();
                        break;
                    }
                    else
                        elementBoardSnakes[i][j] = "___";
                }
            }
        }

        System.out.println();
        System.out.println("Board of Snakes");
        printMatrix(elementBoardSnakes);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for(int index=0; index<getLadders().length; index++) {
                    if (tiles[i][j] == ladders[index].getDownStepId()) {
                        elementBoardLadders[i][j] = "LD" + ladders[index].getLadderId();
                        break;
                    }
                    if (tiles[i][j] == ladders[index].getUpStepId()) {
                        elementBoardLadders[i][j] = "LU" + ladders[index].getLadderId();
                        break;
                    }
                    else
                        elementBoardLadders[i][j] = "___";
                }
            }
        }

        System.out.println();
        System.out.println("Board of Ladders");
        printMatrix(elementBoardLadders);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for(int index=0; index<getApples().length; index++) {
                    if (apples[index].getAppleTileId() == tiles[i][j] && apples[index].getColor().equals("R")) {
                        elementBoardApples[i][j] = "AR" + apples[index].getAppleId();
                        break;
                    }

                    if (apples[index].getAppleTileId() == tiles[i][j] && apples[index].getColor().equals("B")) {
                        elementBoardApples[i][j] = "AB" + apples[index].getAppleId();
                        break;
                    }
                    else
                        elementBoardApples[i][j] = "___";
                }

            }
        }
        System.out.println();
        System.out.println("Board of Apples");
        printMatrix(elementBoardApples);
    }

    public void setN(int N) {
        this.N = N;
    }
    public void setM(int M) {
        this.M = M;
    }
    public void setTiles(int[][] tiles) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }
    public void setSnakes(Snake[] snakes) {
        for (int i = 0; i < snakes.length; i++) {
            this.snakes[i] = snakes[i];
        }
    }
    public void setLadders(Ladder[] ladders) {
        for (int i = 0; i < ladders.length; i++) {
            this.ladders[i] = ladders[i];
        }
    }
    public void setApples(Apple[] apples) {
        for (int i = 0; i < apples.length; i++) {
            this.apples[i] = apples[i];
        }
    }

    public int getN() {
        return N;
    }
    public int getM() {
        return M;
    }
    public int[][] getTiles() {
        return tiles;
    }
    public Snake[] getSnakes() {
        return snakes;
    }
    public Ladder[] getLadders() {
        return ladders;
    }
    public Apple[] getApples() {
        return apples;
    }

    private boolean isDuplicateTail(Snake[] array, int i) {
        for (int j = 0; j < i; j++) {
            if (array[i].getTailId() == array[j].getTailId())
                return true;
        }
        return false;
    }
    private boolean isDuplicateHead(Snake[] array, int i) {
        for (int j = 0; j < i; j++) {
            if (array[i].getHeadId() == array[j].getHeadId() || array[i].getHeadId() == tiles[0][M-1])
                return true;
        }
        return false;
    }
    private boolean isDuplicateDown(Ladder[] array, int i) {
        for (int j=0; j < i; j++) {
            if (array[i].getDownStepId() == array[j].getDownStepId())
                return true;
        }
        for(int j=0; j<getSnakes().length; j++) {
            if(array[i].getDownStepId() == snakes[j].getTailId() || array[i].getDownStepId() == snakes[j].getHeadId() || array[i].getDownStepId() == tiles[N-1][0]) {
                return true;
            }
        }
        return false;
    }
    private boolean isDuplicateUp(Ladder[] array, int i) {
        for (int j = 0; j < i; j++) {
            if (array[i].getUpStepId() == array[j].getUpStepId())
                return true;
        }
        for(int j=0; j<getSnakes().length; j++) {
            if(array[i].getUpStepId() == snakes[j].getHeadId() || array[i].getUpStepId() == snakes[j].getTailId()) {
                return true;
            }
        }
        return false;
    }
    private boolean isDuplicateApples(Apple[] array, int i) {
        for (int j = 0; j<i; j++) {
            if (array[i].getAppleTileId() == array[j].getAppleTileId())
                return true;
        }
        for(int j=0; j<getLadders().length; j++)
        {
            if(array[i].getAppleTileId() == ladders[j].getDownStepId()  || array[i].getAppleTileId() == ladders[j].getUpStepId())
                return true;
        }
        for(int j=0; j<getSnakes().length; j++)
        {
            if(array[i].getAppleTileId() == snakes[j].getTailId() || array[i].getAppleTileId() == snakes[j].getHeadId())
                return true;
        }
        return false;
    }

    private void printMatrix(String[][] matrix) {
        for(int i=0; i<matrix.length; i++)
        {
            for(int j=0; j<matrix[0].length; j++)
            {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}