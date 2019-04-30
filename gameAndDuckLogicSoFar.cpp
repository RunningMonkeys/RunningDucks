#include <cstdio>
#include <cstring>
#include <cstdlib>

const int width = 7;
const int height = 6;
bool exitMode = false; 
bool outOfBounds = false; 
bool isWall = false; 

class Duck {
    private:
      
      int startingX; // the coordinates corresponding to the duck's starting position and previous position
      int startingY;
      
      int previousX;
      int previousY;
      
      int currentX;
      int currentY;
      
      char duckIdentity;
      
      public:
      // getters and setters
      
      int getStartingX() {
          return startingX;
      }
      
      void setStartingX(int x) {
          startingX = x;
      }
      //
      int getStartingY() {
          return startingY;
      }
      
      void setStartingY(int y) {
          startingY = y;
      }
      //
      int getPreviousX() {
          return previousX;
      }
      
      void setPreviousX(int x) {
          previousX = x;
      }
      //
      
      int getPreviousY() {
          return previousY;
      }
      
      void setPreviousY(int y) {
          previousY = y;
      }
      //
      int getCurrentX() {
          return currentX;
      }
      
      void setCurrentX(int x) {
          currentX = x;
      }
      //
      int getCurrentY() {
          return currentY;
      }
      
      void setCurrentY(int y) {
          currentY = y;
      }
      
      char getDuckIdentity() {
          return duckIdentity;
      }
      
      Duck() {
          startingX = 1; // we start at {1,5}
          startingY = 5;
          
          previousX = 1;
          previousY = 5;
          
          currentX = 1;
          currentY = 5;
          
          duckIdentity = '$';
      }
      
};

class board {
    private:
      int boardArr[width][height];
      Duck* protagonist;
    
    public: 
    board() {
        protagonist = new Duck();
        for (int x = 0; x < width; x++) {
            for (int z = 0; z < height; z++) {
                if (x == 0 || x == 6 || z == 5 || z == 0) {
                    boardArr[x][z] = '#';
                }
                else if ((x ==2 && z <=4 && z >=2) || ((x == 3 || x==4) && z == 4) || (x == 4 && z < 3)) {
                    boardArr[x][z] = '#';
                }
                else {
                  boardArr[x][z] = '*';
                }
            }
            
    }
    
    boardArr[protagonist->getStartingX()][protagonist->getStartingY()] = protagonist->getDuckIdentity();
    boardArr[5][0] = '*';
    }
    
    void showBoard() {
        printf("%c %c %c %c %c %c %c\n",boardArr[0][5],boardArr[1][5],boardArr[2][5],boardArr[3][5],boardArr[4][5],boardArr[5][5],boardArr[6][5]);
        printf("%c %c %c %c %c %c %c\n",boardArr[0][4],boardArr[1][4],boardArr[2][4],boardArr[3][4],boardArr[4][4],boardArr[5][4],boardArr[6][4]);
        printf("%c %c %c %c %c %c %c\n",boardArr[0][3],boardArr[1][3],boardArr[2][3],boardArr[3][3],boardArr[4][3],boardArr[5][3],boardArr[6][3]);
        printf("%c %c %c %c %c %c %c\n",boardArr[0][2],boardArr[1][2],boardArr[2][2],boardArr[3][2],boardArr[4][2],boardArr[5][2],boardArr[6][2]);
        printf("%c %c %c %c %c %c %c\n",boardArr[0][1],boardArr[1][1],boardArr[2][1],boardArr[3][1],boardArr[4][1],boardArr[5][1],boardArr[6][1]);
        printf("%c %c %c %c %c %c %c\n",boardArr[0][0],boardArr[1][0],boardArr[2][0],boardArr[3][0],boardArr[4][0],boardArr[5][0],boardArr[6][0]);
    } // format is: boardArr[WIDTH][HEIGHT]
    
    bool isValidLocation(int coordinatePoint,bool isX,int x, int y) {
        if (isX) {
            if (coordinatePoint == 0 || coordinatePoint == 6) {
                printf("\n\nOut of bounds and walled\n");
                return false;
            }
            else if (boardArr[coordinatePoint][y] == '#') {
                printf("\n\nNot out of bounds, but still walled.\n");
                return false; 
            }
        }
        else if (!(isX)) { // must be Y
            if (coordinatePoint == 6) {
                printf("\n\nOut of bounds.\n");
                return false;
            }
            else if (boardArr[x][coordinatePoint] == '#') {
                printf("\n\nNot out of bounds, but still walled!\n");
                return false; 
            }
            
        }
        printf("\n\nUPDATING BOARD!\n\n");
        return true;
        
    }
    
    void updateMaze(int presX,int presY, int playerX,int playerY) {
        boardArr[presX][presY] = '@';
        boardArr[playerX][playerY] = protagonist->getDuckIdentity();
    }
    
      void gameLoop() {
          char userInput;
          while (true) {
            showBoard();
            int potentialX;
            int potentialY;
            bool x;
            int coord;
            printf("Enter a move (a w s d):\n");
            scanf("%c",&userInput);
            if (userInput == 'w') {
                x = false;
                potentialY = protagonist->getCurrentY() + 1; 
                coord = potentialY;
            }
            else if (userInput == 's') {
                x = false;
                potentialY = protagonist->getCurrentY() - 1;
                coord = potentialY;
            }
            else if (userInput == 'a') {
                x = true;
                potentialX = protagonist->getCurrentX() - 1;
                coord = potentialX;
            }
            else if (userInput == 'd') {
                x = true;
                potentialX = protagonist->getCurrentX() + 1;
                coord = potentialX;
            }
            int presentX = protagonist->getCurrentX();
            int presentY = protagonist->getCurrentY();
            if (isValidLocation(coord,x,presentX,presentY)) {
                if (x == false) {
                    protagonist->setPreviousX(presentX);
                    protagonist->setPreviousY(presentY);
                    protagonist->setCurrentX(presentX);
                    protagonist->setCurrentY(coord);
                }
                else {
                    protagonist->setPreviousX(presentX);
                    protagonist->setPreviousY(presentY);
                    protagonist->setCurrentX(coord);
                    protagonist->setCurrentY(presentY);
                }
                
                updateMaze(presentX,presentY,protagonist->getCurrentX(),protagonist->getCurrentY());
                
            }
            continue;
            
            }
          
          
      }
      
};

int main()
{
    board myBoard;
    myBoard.gameLoop();
    return 0;
}
