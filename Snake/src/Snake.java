//Nikolaos Andriotis 9472 6955854088 andriotis@ece.auth.gr
//Vasileios Kyrgyridis 9617 6943059829 vasikyrg@ece.auth.gr
public class Snake {

    private int snakeId;
    private int headId;
    private int tailId;

    public Snake() {
        snakeId = 0;
        headId = 0;
        tailId = 0;
    }
    public Snake(int snakeId, int headId, int tailId) {
        this.snakeId = snakeId;
        this.headId = headId;
        this.tailId = tailId;
    }
    public Snake(Snake snake) {
        this.snakeId = snake.getSnakeId();
        this.headId = snake.getHeadId();
        this.tailId = snake.getSnakeId();
    }

    public int getSnakeId() { return snakeId; }
    public int getHeadId() { return headId; }
    public int getTailId() { return tailId; }

    public void setSnakeId(int snakeId) { this.snakeId = snakeId; }
    public void setHeadId(int headId) { this.headId = headId; }
    public void setTailId(int tailId) { this.tailId = tailId; }
}