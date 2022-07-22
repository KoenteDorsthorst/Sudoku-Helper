import java.util.ArrayList;

public class Square {
    int correctNumber = 0;
    ArrayList<Integer> possibleNumbers = new ArrayList<>();

    Square(int number){
        correctNumber = number;
        for(int i = 1; i < 10; i++){
            possibleNumbers.add(i);
        }
    }

    void removePossibility(int possibility){
        possibleNumbers.remove(Integer.valueOf(possibility));
    }

    public ArrayList<Integer> getPossibleNumbers() {
        return possibleNumbers;
    }

    public void setCorrectNumber(int correctNumber) {
        this.correctNumber = correctNumber;
    }

    public int getCorrectNumber() {
        return correctNumber;
    }
}
