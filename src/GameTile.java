import java.awt.*;

public class GameTile {
    /**
     *
     * @author Antoan
     * @param "Създавам си цвят"
     */
    public static final int TILE_SIZE = 50;

    private float row;
    private float col;
    private float tileSize;

    /**
     *
     * @author Antoan
     * @param "Инициализиране на стойностите за игралната плочка"
     */
    public GameTile(float row, float col) {

        this.row        = row;
        this.col        = col;
        this.tileSize   = 50;
    }
    /**
     *
     * @author Antoan
     * @param "Обединяване на 2та метода за визоализиране в едно"
     */
    public void render(Graphics g){
        RenderCubes(g);
        RenderBorders(g);

    }
    /**
     *
     * @author Antoan
     * @param "Визоализиране на квадратите и на кръгчето в центъра на полето"
     */
    public void RenderCubes(Graphics g) {
        g.setColor(Color.white);
       g.fillRect(50,50,400,400);
    }
    /**
     *
     * @author Antoan
     * @param "Визуализиране на решетката на полето"
     */
    public void RenderBorders(Graphics g){
        g.setColor(Color.black);
        for (int i=50; i<=450 ;i+=50){
            for(int j=50;j<=450;j+=50){
                if(i<=400&&j<=400) {
                    g.fillRect(j, i, 2, 50);
                    g.fillRect(j, i, 50, 2);
                }  if(i==450&&j<450){
                    g.fillRect(j, i, 50, 2);
                }
                if(i<450&&j==450){
                    g.fillRect(j, i, 2, 52);
                }

            }
        }
    }
}