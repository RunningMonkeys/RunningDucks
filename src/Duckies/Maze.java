package sample;



import java.util.List;

public class Maze {
    List<Level>  levels;
    int currentLevel = 0;

    void updateLevel(){
        currentLevel++;
    }
}
