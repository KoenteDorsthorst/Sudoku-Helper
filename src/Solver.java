import java.sql.Array;
import java.util.ArrayList;

public class Solver {

    int boardWidth = 9;
    int boardHeight = 9;

    int squareWidth = 3;
    int squareHeight = 3;


    //Fill sudoku in here
    int[][] initialBoardState = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    Square[][] boardState = new Square[boardHeight][boardWidth];

    void setup() {
        for (int i = 0; i < boardHeight; i++) {
            for(int n = 0; n < boardWidth; n++){
                boardState[i][n] = new Square(initialBoardState[i][n]);
            }
        }
    }

    void solve(){
        for (int i = 0; i < boardHeight; i++) {
            for(int n = 0; n < boardWidth; n++) {



                Square currentSquare = boardState[i][n];
                if(currentSquare.getCorrectNumber() != 0){
                    continue;
                }

                //List of 1 through 9
                ArrayList<Integer> horizontalPossibilities = new ArrayList<>();
                for(int k = 1; k < 10; k++){
                    horizontalPossibilities.add(k);
                }

                //check horizontal
                for(int horizontal = 0; horizontal < boardWidth; horizontal++){
                    if(horizontal == n){
                        continue;
                    }
                    Square thisSquare = boardState[i][horizontal];
                    currentSquare.removePossibility(thisSquare.getCorrectNumber());
                    if(thisSquare.getCorrectNumber() != 0){
                        continue;
                    }
                    for(int possibleNumber : thisSquare.getPossibleNumbers()){
                        horizontalPossibilities.remove(Integer.valueOf(possibleNumber));
                    }

                }

                //List of 1 through 9
                ArrayList<Integer> verticalPossibilities = new ArrayList<>();
                for(int k = 1; k < 10; k++){
                    verticalPossibilities.add(k);
                }

                //check vertical
                for(int vertical = 0; vertical < boardHeight; vertical++) {
                    if(vertical == i){
                        continue;
                    }
                    Square thisSquare = boardState[vertical][n];
                    currentSquare.removePossibility(thisSquare.getCorrectNumber());
                    if(thisSquare.getCorrectNumber() != 0){
                        continue;
                    }
                    for(int possibleNumber : thisSquare.getPossibleNumbers()){
                        verticalPossibilities.remove(Integer.valueOf(possibleNumber));
                    }

                }


                //Get the location of the board square the current square is in
                int extraVertical = (int) (Math.floor(i / squareHeight) * squareHeight);
                int extraHorizontal = (int) (Math.floor(n / squareWidth) * squareWidth);

                //List of 1 through 9
                ArrayList<Integer> squarePossibilities = new ArrayList<>();
                for(int k = 1; k < 10; k++){
                    squarePossibilities.add(k);
                }

                //check board square
                for(int squareVertical = 0; squareVertical < 3; squareVertical++){
                    for(int squareHorizontal = 0; squareHorizontal < 3; squareHorizontal++) {
                        if(squareVertical + extraVertical == i && squareHorizontal + extraHorizontal == n){
                            continue;
                        }
                        Square thisSquare = boardState[squareVertical + extraVertical][squareHorizontal + extraHorizontal];
                        currentSquare.removePossibility(thisSquare.getCorrectNumber());
                        if(thisSquare.getCorrectNumber() != 0){
                            continue;
                        }
                        for(int possibleNumber : thisSquare.getPossibleNumbers()){
                            squarePossibilities.remove(Integer.valueOf(possibleNumber));
                        }
                    }
                }


                //Check if there is one possibility in the board square
                for(int possibleNumber : squarePossibilities){
                    if(currentSquare.getPossibleNumbers().contains(possibleNumber)){
                        currentSquare.setCorrectNumber(possibleNumber);
                    }
                }

                //Check if there is one possibility in the horizontal row
                for(int possibleNumber : horizontalPossibilities){
                    if(currentSquare.getPossibleNumbers().contains(possibleNumber)){
                        currentSquare.setCorrectNumber(possibleNumber);
                    }
                }

                //Check if there is one possibility in the vertical row
                for(int possibleNumber : verticalPossibilities){
                    if(currentSquare.getPossibleNumbers().contains(possibleNumber)){
                        currentSquare.setCorrectNumber(possibleNumber);
                    }
                }

                //If only one solution, solve
                if(currentSquare.getPossibleNumbers().size() == 1){
                    currentSquare.setCorrectNumber(currentSquare.getPossibleNumbers().get(0));
                }
            }
        }
    }

    void printBoardState(){
        for (int i = 0; i < boardHeight; i++) {
            for (int n = 0; n < boardWidth; n++) {
                System.out.print(boardState[i][n].correctNumber + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

}
