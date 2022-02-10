//Nikolaos Andriotis 9472 6955854088 andriotis@ece.auth.gr
//Vasileios Kyrgyridis 9617 6943059829 vasikyrg@ece.auth.gr
public class Ladder {

    private int ladderId;
    private int upStepId;
    private int downStepId;
    private boolean broken;

    public Ladder() {
        ladderId = 0;
        upStepId = 0;
        downStepId = 0;
        broken = false;
    }
    public Ladder(int ladderId, int upStepId, int downStepId, boolean broken) {
        this.ladderId = ladderId;
        this.upStepId = upStepId;
        this.downStepId = downStepId;
        this.broken = broken;
    }
    public Ladder(Ladder ladder) {
        this.ladderId = ladder.getLadderId();
        this.upStepId = ladder.getUpStepId();
        this.downStepId = ladder.getDownStepId();
        this.broken = ladder.getBroken();
    }

    public int getLadderId() { return ladderId; }
    public int getUpStepId() { return upStepId; }
    public int getDownStepId() { return downStepId; }
    public boolean getBroken() { return broken;}

    public void setLadderId(int ladderId) { this.ladderId = ladderId; }
    public void setUpStepId(int upStepId) { this.upStepId = upStepId; }
    public void setDownStepId(int downStepId) { this.downStepId = downStepId; }
    public void setBroken(boolean broken) { this.broken = broken; }
}