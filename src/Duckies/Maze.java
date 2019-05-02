package Duckies;



public class Maze {
    int[][] level = {{2,7,2,0,5},{2,0,3,0,3},{2,0,2,0,0},{2,0,2,2,0},{2,0,0,0,0}};
    Duck duck;

    public Maze()
    {
        duck = new Duck();

    }

    public void printMaze()
    {
        for(int i = 0; i< level.length; i++)
        {
            for(int j = 0; j < level[i].length; j++)
            {
                System.out.print(level[i][j]);
            }
            System.out.println();
        }
    }

    public String getPlayerMaze(int player)
    {
        int p = getPlayerPrime(player);
        String out = "" + level.length +", " + level[0].length + ":";
        for(int i = 0; i< level.length; i++)
        {
            for(int j = 0; j < level[i].length; j++)
            {
                if(level[i][j] == p)
                {
                    out = out + "1";
                }
                else
                {
                    out = out + "0";
                }
            }
        }

        return out;
        //return play;
    }

    private int getPlayerPrime(int player)
    {
        switch(player)
        {
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 5;
            case 4:
                return 7;
            default:
                return 420;
        }
    }

    public boolean moveDuck(String a){
        a = a.toLowerCase();
        switch(a)
        {
            case "up":
                if(duck.getCurrentY() - 1 < 0)
                {
                    return false;
                }
                else if(level[duck.getCurrentY() -1][duck.getCurrentX()] == 5)
                {
                    level[duck.getCurrentY()][duck.getCurrentX()] = 0;
                    level[duck.getCurrentY() -1][duck.getCurrentX()] = 5*7;
                    System.out.println("win");
                    duck.setCurrentY(duck.getCurrentY()-1); //duckY = duckY -1;
                    return true;
                }
                else if (level[duck.getCurrentY()-1][duck.getCurrentX()] == 0)//else if(level[duckY -1][duckX] == 0)
                {
                    level[duck.getCurrentY()][duck.getCurrentX()] = 0;
                    level[duck.getCurrentY()-1][duck.getCurrentX()] = 7; //level[duckY -1][duckX] = 7;
                    duck.setCurrentY(duck.getCurrentY()-1); //duckY = duckY -1;
                    System.out.println("Duck at Y = " +duck.getCurrentY()+" X = "+ duck.getCurrentX());
                    return false;
                }
                else if (level[duck.getCurrentY()-1][duck.getCurrentX()] % 2 ==0)// else if(level[duckY-1][duckX] % 2 == 0)
                {
                    System.out.println("Duck at Y = " +duck.getCurrentY()+" X = "+ duck.getCurrentX());
                    return false;
                }
                else if (level[duck.getCurrentY()-1][duck.getCurrentX()]%3 ==0)//else if(level[duckY-1][duckX]%3 == 0)
                {
                    level[duck.getCurrentY()][duck.getCurrentX()] = 0;
                    level[duck.getStartingY()][duck.getCurrentX()] = 7;
                    duck.setCurrentX(duck.getStartingX()); //duckX = duckInitX;
                    duck.setCurrentY(duck.getStartingY()); //duckY = duckInitY;
                    System.out.println("Duck at Y = " +duck.getCurrentY()+" X = "+ duck.getCurrentX());
                    return false;
                }
                break;
            case "down":
                if (duck.getCurrentY()+1 >= level.length)//if(duckY +1 >= level.length)
                {
                    return false;
                }
                else if(level[duck.getCurrentY()+1][duck.getCurrentX()] == 5)
                {
                    level[duck.getCurrentY()][duck.getCurrentX()] = 0;
                    level[duck.getCurrentY() +1][duck.getCurrentX()] = 5*7;
                    duck.setCurrentY(duck.getCurrentY()+1);//duckY = duckY +1;
                    System.out.println("Duck at Y = " +duck.getCurrentY()+" X = "+ duck.getCurrentX());
                    return true;
                }
                else if(level[duck.getCurrentY() +1][duck.getCurrentX()] == 0)
                {
                    level[duck.getCurrentY()][duck.getCurrentX()] = 0;
                    level[duck.getCurrentY() +1][duck.getCurrentX()] = 7;
                    duck.setCurrentY(duck.getCurrentY()+1);//duckY = duckY +1;
                    System.out.println("Duck at Y = " +duck.getCurrentY()+" X = "+ duck.getCurrentX());
                    return false;
                }
                else if(level[duck.getCurrentY()+1][duck.getCurrentX()] % 2 == 0)
                {
                    System.out.println("Duck at Y = " +duck.getCurrentY()+" X = "+ duck.getCurrentX());
                    return false;
                }
                else if(level[duck.getCurrentY()+1][duck.getCurrentX()]%3 == 0)
                {
                    level[duck.getCurrentY()][duck.getCurrentX()] = 0;
                    level[duck.getStartingY()][duck.getStartingX()] = 7;
                    duck.setCurrentX(duck.getStartingX());//duckX = duckInitX;
                    duck.setCurrentY(duck.getStartingY());//duckY = duckInitY;
                    System.out.println("Duck at Y = " +duck.getStartingY()+" X = "+ duck.getStartingX());
                    return false;
                }
                break;
            case "left":
                if(duck.getCurrentX() - 1 < 0 || duck.getCurrentY() <0)
                {
                    return false;
                }
                else if(level[duck.getCurrentY()][duck.getCurrentX()-1] == 5)
                {
                    level[duck.getCurrentY()][duck.getCurrentX()] = 0;
                    level[duck.getCurrentY()][duck.getCurrentX()-1] = 5*7;
                    duck.setCurrentX(duck.getCurrentX()-1);//duckX = duckX -1;
                    return true;
                }
                else if(level[duck.getCurrentY()][duck.getCurrentX()-1] == 0)
                {
                    level[duck.getCurrentY()][duck.getCurrentX()] = 0;
                    level[duck.getCurrentY()][duck.getCurrentX()-1] = 7;
                    duck.setCurrentX(duck.getCurrentX()-1);//duckX = duckX -1;
                    return false;
                }
                else if(level[duck.getCurrentY()][duck.getCurrentX()-1] % 2 == 0)
                {
                    return false;
                }
                else if(level[duck.getCurrentY()][duck.getCurrentX()-1]%3 == 0)
                {
                    level[duck.getCurrentY()][duck.getCurrentX()] = 0;
                    level[duck.getStartingY()][duck.getStartingX()] = 7;
                    duck.setCurrentX(duck.getStartingX());//duckX = duckInitX;
                    duck.setCurrentX(duck.getStartingY());//duckY = duckInitY;
                    return false;
                }
                break;
            case "right":
                if(duck.getCurrentX() +1 >= level.length)
                {
                    System.out.println("Duck at Y = " +duck.getCurrentY()+" X = "+ duck.getCurrentX());
                    return false;
                }
                else if(level[duck.getCurrentY()][duck.getCurrentX()+1] == 5)
                {
                    level[duck.getCurrentY()][duck.getCurrentX()] = 0;
                    level[duck.getCurrentY()][duck.getCurrentX()+1] = 5*7;
                    duck.setCurrentX(duck.getCurrentX()+1);//duckX = duckX +1;
                    System.out.println("Duck at Y = " +duck.getCurrentY()+" X = "+ duck.getCurrentX());
                    return true;
                }
                else if(level[duck.getCurrentY()][duck.getCurrentX()+1] == 0)
                {
                    level[duck.getCurrentY()][duck.getCurrentX()] = 0;
                    level[duck.getCurrentY()][duck.getCurrentX()+1] = 7;
                    duck.setCurrentX(duck.getCurrentX()+1);//duckX = duckX +1;
                    System.out.println("Duck at Y = " +duck.getCurrentY()+" X = "+ duck.getCurrentX());
                    return false;
                }
                else if(level[duck.getCurrentY()][duck.getCurrentX()+1] % 2 == 0)
                {
                    System.out.println("Duck at Y = " +duck.getCurrentY()+" X = "+ duck.getCurrentX());
                    return false;
                }
                else if(level[duck.getCurrentY()][duck.getCurrentX()+1]%3 == 0)
                {
                    level[duck.getCurrentY()][duck.getCurrentX()] = 0;
                    level[duck.getStartingY()][duck.getStartingX()] = 7;
                    duck.setCurrentX(duck.getStartingX());//duckX = duckInitX;
                    duck.setCurrentY(duck.getStartingY());//duckY = duckInitY;
                    System.out.println("Duck at Y = " +duck.getCurrentY()+" X = "+ duck.getCurrentX());
                    return false;
                }
                break;
            default:
                return false;
        }
        return false;
    }
}