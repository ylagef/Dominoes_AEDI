
public class Token {

    private int side1;
    private int side2;

    public Token(int side1, int side2) {
        this.side1 = side1;
        this.side2 = side2;
    }

    public int getSide1() {
        return side1;
    }

    public void setSide1(int side1) {
        this.side1 = side1;
    }

    public int getSide2() {
        return side2;
    }

    public void setSide2(int side2) {
        this.side2 = side2;
    }

    @Override
    public String toString() {
        return "[" + side1 + "|" + side2 + "]";
    }

    public int rightCount(int number) {
        int rightCount = 0;

        if (side1 == number) {
            rightCount++;
        }
        if (side2 == number) {
            rightCount++;
        }
        return rightCount;
    }

    public int leftCount(int number) {
        int leftCount = 0;
        if (side1 == number) {
            leftCount++;
        }
        if (side2 == number) {
            leftCount++;
        }
        return leftCount;
    }
}
