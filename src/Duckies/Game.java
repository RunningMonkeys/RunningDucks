package sample;



import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Game {
    Maze maze;
    List<String> solns = Arrays.asList("lvl0.1","lvl0.2","lvl0.3","lvl0.4",
                                        "lvl1.1","lvl1.2","lvl1.3","lvl1.4");
    List<Client> players;
    // constructor
    Game(){
        maze = new Maze();
    }

    String sendClues(){

        int i = new Random(System.currentTimeMillis()).nextInt(5);

        String toBeSent = solns.get(i);
        solns.remove(i);
        return toBeSent;
    }

    void updateLevel(){
        maze.updateLevel();
        sendClues();
    }
}
