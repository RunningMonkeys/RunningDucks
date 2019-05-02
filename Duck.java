public class Duck {

    private int startingX;
    private int startingY;

    private int previousX;
    private int previousY;

    private int currentX;
    private int currentY;

    private char duckIdentity;

    public int getStartingX() {
        return startingX;
    }

    public void setStartingX(int x) {
        startingX = x;
    }
    //
    public int getStartingY() {
        return startingY;
    }

    public void setStartingY(int y) {
        startingY = y;
    }
    //
    public int getPreviousX() {
        return previousX;
    }

    public void setPreviousX(int x) {
        previousX = x;
    }
    //

    public int getPreviousY() {
        return previousY;
    }

    public void setPreviousY(int y) {
        previousY = y;
    }
    //
    public int getCurrentX() {
        return currentX;
    }

    public void setCurrentX(int x) {
        currentX = x;
    }
    //
    public int getCurrentY() {
        return currentY;
    }

    public void setCurrentY(int y) {
        currentY = y;
    }

    public char getDuckIdentity() {
        return duckIdentity;
    }

    Duck() {
        startingX = 1; // we start at {1,5}
        startingY = 0;

        previousX = 1;
        previousY = 0;

        currentX = 1;
        currentY = 0;

        duckIdentity = '$';
    }
    
    Duck(int userX, int userY) {
        startingX = userX;
        startingY = userY;
        previousX = userX;
        previousY = userY;
        currentX = userX;
        currentY = userY;
        duckIdentity = '$';
    }

}