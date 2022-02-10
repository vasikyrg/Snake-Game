//Nikolaos Andriotis 9472 6955854088 andriotis@ece.auth.gr
//Vasileios Kyrgyridis 9617 6943059829 vasikyrg@ece.auth.gr
public class Apple {

    private int appleId;
    private int appleTileId;
    private String color;
    private int points;

    public Apple() {
        appleId = 0;
        appleTileId = 0;
        color = "";
    }
    public Apple(int appleId, int appleTileId, String color, int points){
        this.appleId = appleId;
        this.appleTileId = appleTileId;
        this.color = color;
        this.points = points;
    }
    public Apple(Apple apple) {
        this.appleId = apple.getAppleId();
        this.appleTileId = apple.getAppleTileId();
        this.color = apple.getColor();
        this.points = apple.getPoints();
    }

    public int getAppleId() { return appleId; }
    public int getAppleTileId() { return appleTileId; }
    public String getColor() { return color; }
    public int getPoints() { return points; }

    public void setAppleId(int appleId) { this.appleId = appleId; }
    public void setAppleTileId(int appleTileId) { this.appleTileId = appleTileId; }
    public void setColor(String color) { this.color = color; }
    public void setPoints(int points) { this.points = points; }
}
