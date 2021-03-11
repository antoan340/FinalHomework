import java.awt.*;
/**
 *
 * @author Antoan
 * @param "Пазача наследява стойностите на фигурата"
 */
public class Traps extends Figure {
    public Color color;
    /**
     * @param "Задаване на параметри на непроходимите местности"
     * @author Antoan
     */
    public Traps(int row, int col, Color color) {
        super();
        this.row = row;
        this.col=col;
        this.color=color;

    }
    /**
     *
     * @author Antoan
     * @param "Визуализиране на непроходимите местности"
     */
    public void render(Graphics g) {
        int x = this.col * GameTile.TILE_SIZE;
        int y = this.row * GameTile.TILE_SIZE;
        g.setColor(this.color);
        g.fillRect(x, y, 50, 50);
    }

}