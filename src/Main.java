public class Main {

    public static void main(String args[]){
        Solver solver = new Solver();
        solver.setup();
        //Tries to solve 100 times, will probably solve any sudoku in this time
        for(int i = 0; i < 100; i ++){
            solver.solve();
        }
        solver.printBoardState();
    }
}
