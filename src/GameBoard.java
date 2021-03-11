
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Antoan
 * @param "проектиране на игралното поле"
 */
public class GameBoard extends JFrame implements MouseListener {
    protected Traps[][] traps;
    protected Snake[][] snake;
    protected Food[][] food;
    int randOne,randTwo,trapCount;
    protected Snake snakeHead;
    int rowStart,colStart,rowEnd,colEnd,rowNearEnd,colNearEnd;
    int points=0;
    JLabel label = new JLabel();
    /**
     * @param "Създаване на игралното поле"
     * @author Antoan
     */
    public GameBoard() {
        label.setText("Points = 0");

        label.setBounds(30,-15,100,50);
        this.add(label);
        this.setLayout(null);
        spawnSnake();
        randomTraps();
        randomFoodSpawn();

        setTitle("Mythic snake");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addMouseListener(this);

    }

    public void randomTraps(){
        this.traps = new Traps[9][9];
        trapCount=8;
        do{
            randOne = ThreadLocalRandom.current().nextInt(1,9);
            randTwo = ThreadLocalRandom.current().nextInt(1,9);
            if(traps[randOne][randTwo]==null&&snake[randOne][randTwo]==null) {
                this.traps[randOne][randTwo] = new Traps(randOne,randTwo,Color.BLACK);
                trapCount--;
            }
        }while (trapCount>0);
    }
    public void randomFoodSpawn(){
        this.food = new Food[9][9];
        randOne = ThreadLocalRandom.current().nextInt(1,9);
        randTwo = ThreadLocalRandom.current().nextInt(1,9);
        if(snake[randOne][randTwo]==null && traps[randOne][randTwo]==null){
            this.food[randOne][randTwo] = new Food(randOne,randTwo,Color.PINK);
        }
        else  randomFoodSpawn();
    }
    public void spawnSnake(){
        this.snake = new Snake[9][9];
        randOne = ThreadLocalRandom.current().nextInt(1,4);
        switch (randOne) {
            case 1 -> this.snake[1][1] = (new Snake(1, 1, Color.YELLOW));
            case 2 -> this.snake[8][1] = (new Snake(8, 1, Color.YELLOW));
            case 3 -> this.snake[1][8] = (new Snake(1, 8, Color.YELLOW));
            default ->this.snake[8][8] = (new Snake(8, 8, Color.YELLOW));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = this.getBoardDimentionBasedOnCoordinates(e.getY());
        int col = this.getBoardDimentionBasedOnCoordinates(e.getX());
        if ((row < 9 && col < 9)) {
            if (this.snakeHead != null) {
                if (this.hasFood(row, col)) {
                    points += 15;
                    label.setText("Points = " + points);
                    if (points == 300)
                        Modal.render(this, "Atention", "You win");
                }
                if (points < 15) {
                    this.snake[row][col] = new Snake(row, col, Color.YELLOW);
                    this.snake[rowStart][colStart] = null;
                    snakeHead = null;
                }
                repaint();
            } else {
                rowStart = row;
                colStart = col;
                if (this.hasSnake(row, col)) {
                    this.snakeHead = this.getSnake(row, col);
                }

            }
        }
    }
   @Override
    public void paint(Graphics g) {

        super.paint(g);
        GameTile tile = new GameTile(0, 0);
        tile.render(g);
        for (int row = 0; row <9; row++) {
            for (int col = 0; col < 9; col++) {
                renderGamePiece(g, row, col);
            }
        }

    }

    /**
     * @param "обекта "гард
     * @author Antoan
   */
    private Traps getTrap(int row, int col) {
        return this.traps[row][col];

    }

    /**
     * @param "проверка за дали гарда не е нулл"
     * @author Antoan
     * */

    private boolean hasTrap(int row, int col) {
        return this.getTrap(row, col) != null;
    }
    private Snake getSnake(int row, int col) {
        return this.snake[row][col];

    }

    private boolean hasSnake(int row, int col) {
        return this.getSnake(row, col) != null;
    }
    private Food getFood(int row, int col) {
        return this.food[row][col];

    }

    private boolean hasFood(int row, int col) {
        return this.getFood(row, col) != null;
    }

    private void renderGamePiece(Graphics g, int row, int col) {
        if (this.hasTrap(row, col)) {
            Traps p = (Traps) this.getTrap(row, col);
            p.render(g);

        }
        if (this.hasSnake(row, col)) {
            Snake p1 = (Snake) this.getSnake(row, col);
            p1.render(g);

        }
        if (this.hasFood(row, col)) {
            Food p2 = (Food) this.getFood(row, col);
            p2.render(g);

        }
    }



    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    /**
     *
     * @author Antoan
     * @param "координатите на играта"
     */
    private int getBoardDimentionBasedOnCoordinates(int coordinates) {
        return coordinates / GameTile.TILE_SIZE;
    }
}