/**
* Name: Yuvaraj Gambhir
* Pennkey: 12279760
* Execution: n/a cell class
*
* Description: creates the sudoku suite that controls board and cells
**/
public class Sudoku {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Please enter an input board!");
        }
        
        String inputBoard = args[0];
        int i;
        int j;
        char keyTyped;
        boolean isWinner = false;
        
        Board board = new Board(inputBoard);
        if (board.isBoardValid() == false) {
            throw new IllegalArgumentException("This board ain't good!");
        }
        board.draw();
        
        while (isWinner == false) {
            if (PennDraw.hasNextKeyTyped()) {
                boolean RowRuleBreach = false;
                boolean ColumnRuleBreach = false;
                
                keyTyped = PennDraw.nextKeyTyped();
                
                j = (int) Math.floor(PennDraw.mouseX() / (Cell.halfWidth * 2));
                i = (int) Math.floor((1-PennDraw.mouseY()) / (Cell.halfWidth * 2));
                
                if (board.getCells()[i][j].getIsOriginal() == false) {
                    board.getCells()[i][j].setValue(keyTyped);
                    board.draw();
                }
                
                isWinner = board.isWinner();
            }
        }
        //prints to command-line that user has won!
        System.out.println("OMG. You won?!");
    }
}

