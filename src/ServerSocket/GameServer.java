package ServerSocket;

public class GameServer {
    private final int sizeXGame = 3;
    private final int sizeYGame = 6;
    private final int arrMatrix[][];
    private boolean tick[][];

    public int getSizeXGame() {
        return sizeXGame;
    }

    public int getSizeYGame() {
        return sizeYGame;
    }

    public int[][] getArrMatrix() {
        return arrMatrix;
    }

    public boolean[][] getTick() {
        return tick;
    }

    public GameServer(){
        this.arrMatrix = new int[sizeXGame][sizeYGame];
        this.tick = new boolean[sizeXGame][sizeYGame];
        for (int i = 0; i < sizeXGame; i++) {
            for (int j = 0; j < sizeYGame; j++) {
                arrMatrix[i][j] = (int) (Math.random() * 2 + 1);
                tick[i][j] = true;
            }
        }
    }
}
