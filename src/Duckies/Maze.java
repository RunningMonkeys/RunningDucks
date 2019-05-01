package Duckies;



public class Maze {
    int[][] level = {{2,7,2,0,5},{2,0,3,0,3},{2,0,2,0,0},{2,0,2,2,0},{2,0,0,0,0}};
    int duckX;
    int duckY;
    int duckInitX;
    int duckInitY;
    
    public Maze()
    {
    	
    	duckX = 1;
    	duckY = 0;
    	duckInitX = 1;
    	duckInitY = 0;
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
    
    public boolean[][] getPlayerMaze(int player)
    {
    	int p = getPlayerPrime(player);
    	boolean[][] play = new boolean[level.length][level[0].length];
    	for(int i = 0; i< level.length; i++)
    	{
    		for(int j = 0; j < level[i].length; j++)
    		{
    			play[i][j] = (level[i][j]%p == 0);
    		}
    	}
    	return play;
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
    		if(duckY - 1 < 0)
    		{
    			return false;
    		}
    		else if(level[duckY -1][duckX] == 5)
    		{
    			level[duckY][duckX] = 0;
    			level[duckY -1][duckX] = 5*7;
    			System.out.println("win");
    			duckY = duckY -1;
    			return true;
    		}
    		else if(level[duckY -1][duckX] == 0)
    		{
    			level[duckY][duckX] = 0;
    			level[duckY -1][duckX] = 7;
    			duckY = duckY -1;
    			System.out.println("Duck at Y = " +duckY+" X = "+ duckX);
    			return false;
    		}
    		else if(level[duckY-1][duckX] % 2 == 0)
    		{
    			System.out.println("Duck at Y = " +duckY+" X = "+ duckX);
    			return false;
    		}
    		else if(level[duckY-1][duckX]%3 == 0)
    		{
    			level[duckY][duckX] = 0;
    			level[duckInitY][duckInitX] = 7;
    			duckX = duckInitX;
    			duckY = duckInitY;
    			System.out.println("Duck at Y = " +duckY+" X = "+ duckX);
    			return false;
    		}
    		break;
    	case "down":
    		if(duckY +1 >= level.length)
    		{
    			return false;
    		}
    		else if(level[duckY +1][duckX] == 5)
    		{
    			level[duckY][duckX] = 0;
    			level[duckY +1][duckX] = 5*7;
    			duckY = duckY +1;
    			System.out.println("Duck at Y = " +duckY+" X = "+ duckX);
    			return true;
    		}
    		else if(level[duckY +1][duckX] == 0)
    		{
    			level[duckY][duckX] = 0;
    			level[duckY +1][duckX] = 7;
    			duckY = duckY +1;
    			System.out.println("Duck at Y = " +duckY+" X = "+ duckX);
    			return false;
    		}
    		else if(level[duckY+1][duckX] % 2 == 0)
    		{
    			System.out.println("Duck at Y = " +duckY+" X = "+ duckX);
    			return false;
    		}
    		else if(level[duckY+1][duckX]%3 == 0)
    		{
    			level[duckY][duckX] = 0;
    			level[duckInitY][duckInitX] = 7;
    			duckX = duckInitX;
    			duckY = duckInitY;
    			System.out.println("Duck at Y = " +duckY+" X = "+ duckX);
    			return false;
    		}
    		break;
    	case "left":
    		if(duckX - 1 < 0 || duckY <0)
    		{
    			return false;
    		}
    		else if(level[duckY][duckX-1] == 5)
    		{
    			level[duckY][duckX] = 0;
    			level[duckY][duckX-1] = 5*7;
    			duckX = duckX -1;
    			return true;
    		}
    		else if(level[duckY][duckX-1] == 0)
    		{
    			level[duckY][duckX] = 0;
    			level[duckY][duckX-1] = 7;
    			duckX = duckX -1;
    			return false;
    		}
    		else if(level[duckY][duckX-1] % 2 == 0)
    		{
    			return false;
    		}
    		else if(level[duckY][duckX-1]%3 == 0)
    		{
    			level[duckY][duckX] = 0;
    			level[duckInitY][duckInitX] = 7;
    			duckX = duckInitX;
    			duckY = duckInitY;
    			return false;
    		}
    		break;
    	case "right":
    		if(duckX +1 >= level.length)
    		{
    			System.out.println("Duck at Y = " +duckY+" X = "+ duckX);
    			return false;
    		}
    		else if(level[duckY][duckX+1] == 5)
    		{
    			level[duckY][duckX] = 0;
    			level[duckY][duckX+1] = 5*7;
    			duckX = duckX +1;
    			System.out.println("Duck at Y = " +duckY+" X = "+ duckX);
    			return true;
    		}
    		else if(level[duckY][duckX+1] == 0)
    		{
    			level[duckY][duckX] = 0;
    			level[duckY][duckX+1] = 7;
    			duckX = duckX +1;
    			System.out.println("Duck at Y = " +duckY+" X = "+ duckX);
    			return false;
    		}
    		else if(level[duckY][duckX+1] % 2 == 0)
    		{
    			System.out.println("Duck at Y = " +duckY+" X = "+ duckX);
    			return false;
    		}
    		else if(level[duckY][duckX+1]%3 == 0)
    		{
    			level[duckY][duckX] = 0;
    			level[duckInitY][duckInitX] = 7;
    			duckX = duckInitX;
    			duckY = duckInitY;
    			System.out.println("Duck at Y = " +duckY+" X = "+ duckX);
    			return false;
    		}
    		break;
    	default:
    		return false;
    	}
    	return false;
    }
}
