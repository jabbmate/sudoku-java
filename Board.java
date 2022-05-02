/**
* Name: Yuvaraj Gambhir
* Pennkey: 12279760
* Execution: n/a Board class
*
* Description: creates a board from the input file and maintains cells array
**/
public class Board {
    
    //instance variables
    private Cell[][] cells;
    
    


    public Board(String filename) {
        char c;
        In inStream = new In(filename);
        
        this.cells = new Cell[9][9];
        
        for (int i = 0; i < 9; i++) {
            if (inStream.isEmpty()) {
                //throw new IllegalArgumentException("Your file doesn't have enough rows!");
            }
            String s = inStream.readLine();
            if (s.length() != 9) {
                //throw new IllegalArgumentException("A row in your file doesn't have exactly 9 characters!");
            }
            for (int j = 0; j < 9; j++) {
                c = s.charAt(j);
                if ((c != ' ') && ((c < '1') || (c > '9'))) {
                    //throw new IllegalArgumentException("Your file has invalid characters!");
                }
                double posX = (2 * j + 1) * Cell.halfWidth;
                double posY = 1 - (2 * i + 1) * Cell.halfWidth;
                this.cells[i][j] = new Cell(c, posX, posY);
                
            }
        }
        
        if (inStream.isEmpty() == false) {
            throw new IllegalArgumentException("Your file has too many rows!");
        }
        
    }
    
    /*
     * Inputs: none
     * Outputs: boolean
    *  Description: Checks if the board is valid
    */
    public boolean isBoardValid() {
        int i, j;
        
        for (i = 0; i < 9; i++) {
            if (isBreachRow(i) == true) {
                return false;
            }
            for (j = 0; j < 9; j++) {
                if (isBreachColumn(j) == true) {
                    return false;
                }
                if (isBreachBox(i, j) == true) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
    * Inputs: none
    * Outputs: matrix of cells
    * Description: cell matrix getter method
    */
    public Cell[][] getCells() {
        return this.cells;
    }
    
    /*
     * Inputs: position of upper left row for box,
     * position of upper left column for box, value 
     * Output: boolean
    * Description:check if the box with upper left corner specified
    * contains the value v
    */
    private boolean doesBoxContain(int upperLeftRow, int upperLeftColumn, int v) {
        int i, j;
        
        for (i = upperLeftRow; i <= upperLeftRow + 2; i++) {
            for (j = upperLeftColumn; j <= upperLeftColumn + 2; j++) {
                if (this.cells[i][j].getValue() ==  v) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /*
     * Inputs: int n
     * Outputs: boolean
    * Description: check if box n is complete where boxes are numbered
    * 0 to 8 with top row having 0, 1, 2. second row having 3,4,5
    * and so on...
    */
    private boolean isBoxComplete(int n) {
        int upperLeftColumn;
        int upperLeftRow;
        int v;
        
        upperLeftColumn = 3 * (n % 3);
        upperLeftRow = 3 * (int) Math.floor(n / 3);
        
        for (v = 49; v <= 57; v++) {
            if (doesBoxContain(upperLeftRow, upperLeftColumn, v) == false) {
                return false;
            }
        }
        
        return true;
    }
    
    /*Inputs: int j , int v
     * Outputs: boolean value
    * Description: check if column j contains value v
    */
    private boolean doesColumnContain(int j, int v) {
        int i;
        
        for (i = 0; i < 9; i++) {
            if (this.cells[i][j].getValue() == v) {
                return true;
            }
        }
        
        return false;
    }
    
    /*
     * Inputs: int j
     * Outputs: boolean value
    *Description: check if the column is complete
    */
    private boolean isCompleteColumn(int j) {
        int v;
        
        for (v = 49; v <= 57; v++) {
            if (doesColumnContain(j, v) == false) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Inputs: int i, int v
    * Outputs: boolean
    * Description: check if a row contains the value v
    */
    private boolean doesRowContain(int i, int v) {
        int j;
        
        for (j = 0; j < 9; j++) {

            if (this.cells[i][j].getValue() == v) {
                return true;
            }
        }
        
        return false;
    }
    
    /**Inputs: int i
     * Outputs: boolean value
    * Description: Check if the row is complete
    */
    private boolean isCompleteRow (int i) {
        int v;
        
        for (v = 49; v <= 57; v++) {
            if (doesRowContain(i, v) == false) {
    
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Inputs: none
outputs:boolean value
    * Description: Check if the board is a winner
    */
    public boolean isWinner() {
        int n;
        
        for (n = 0 ; n < 9 ; n++) {
            if (isCompleteRow(n) == false) {
   
                return false;
            }
            
            if (isCompleteColumn(n) == false) {

                return false;
            }
            
            if (isBoxComplete(n) == false) {

                return false;
            }
        }
        
        return true;
        
    }
    
    /**
     * Inputs: int j
     * Outputs: boolean value
     * Description: Checks if column has cells that breach rules
    */
    private boolean isBreachColumn(int j) {
        int n, m;
        

        for (n = 0; n < 8; n++) {
            for (m = 0; m < 8; m++) {
                if (n != m) {
                    if (this.cells[n][j].getValue() != ' ') {
                        if (this.cells[n][j].getValue() == this.cells[m][j].getValue()) {
                            return true;
                        }
                    }
                }
            }
        }
        
        return false;
    }
    
    /**
     * Inputs: int i
     * Outputs: boolean value
     * Description: Checks if row has cells that breach rules
    */
    private boolean isBreachRow(int i) {
        int n, m;
   
        for (n = 0; n < 8; n++) {
            for (m = 0; m < 8; m++) {
                if (n != m) {
                    if (this.cells[i][n].getValue() != ' ') {
                        if (this.cells[i][n].getValue() == this.cells[i][m].getValue()) {
                            return true;
                        }
                    }
                }
            }
        }
        
        return false;
        
    }
    
    /**
     * Inputs: int i, int j
     * Outputs: boolean value
     * Description: Checks if grid box has cells that breach rules
    */
    private boolean isBreachBox(int i, int j) {
        int upperLeftColumn;
        int upperLeftRow;
        int n, m, x, y;
        
        upperLeftRow = 3 * (int) Math.floor(i / 3);
        upperLeftColumn = 3 * (int) Math.floor(j / 3);

        
        for (x = upperLeftRow; x <= upperLeftRow + 2; x++) {
            for (y = upperLeftColumn; y <= upperLeftColumn + 2; y++) {
                for (m = upperLeftRow; m <= upperLeftRow + 2; m++) {
                    for (n = upperLeftColumn; n <= upperLeftColumn + 2; n++) {
                        if ( (x != m) && (y != n)) {
                            if (this.cells[m][n].getValue() != ' ') {
                                if (this.cells[m][n].getValue() == this.cells[x][y].getValue()) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return false;
    }
    

    /**
     * Inputs: int i, int j
     * Outputs: boolean
     * Description:  hasDuplicate will check if there is a duplicate non-space value
    // in the row, column or box
    */
    private boolean hasDuplicate(int i, int j) {
        int m, n, upperLeftColumn, upperLeftRow;
        
        if (this.cells[i][j].getValue() == ' ') {
            return false;
        }
        
        //first, check if there is a duplicate in the row
        for (m = 0; m < 8; m++) {
            if (m != j) {
                if (this.cells[i][j].getValue() == this.cells[i][m].getValue()) {
                    return true;
                }
            }
        }
        
        // now, check if there is a duplicate in the column
        for (m = 0; m < 8; m++) {
            if (m != i) {
                if (this.cells[i][j].getValue() == this.cells[m][j].getValue()) {
                    return true;
                }
            }
        }
        
        // now, let's check if there is a duplicate in the box
        upperLeftRow = 3 * (int) Math.floor(i / 3);
        upperLeftColumn = 3 * (int) Math.floor(j / 3);
        
        for (m = upperLeftRow; m <= upperLeftRow + 2; m++) {
            for (n = upperLeftColumn; n <= upperLeftColumn + 2; n++) {
                if ((i != m) && (j != n)) {
                    if (this.cells[i][j].getValue() == this.cells[m][n].getValue()) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    /**
    * Inputs: none
    * Outputs: none
    * Description: draws the cells and handles the rules
    */
    public void draw() {
        for (int i = 0; i < 9; i++) {
            boolean breachRow = isBreachRow(i);
            
            for (int j = 0; j < 9; j++) {
                boolean breachColumn = isBreachColumn(j);
                boolean breachBox = isBreachBox(i, j);
                
                if (breachBox || breachColumn || breachRow) {
                    if (hasDuplicate(i, j) == true) {
                        if (this.cells[i][j].getIsOriginal() == true) {
                            this.cells[i][j].draw(183, 109, 85, 255, 0, 0);
                        }
                        else {
                            this.cells[i][j].draw(255, 0, 0, 0, 0, 0);
                        }
                        
                    }
                    else {
                        if (this.cells[i][j].getIsOriginal() == true){
                            this.cells[i][j].draw(183, 109, 85, 255, 255, 0);
                        }
                        else {
                            this.cells[i][j].draw(255, 255, 0, 0, 0, 0);
                        }
                        
                    }
                }
                else {   // if there is no breach
                    if (this.cells[i][j].getIsOriginal() == true) {
                        this.cells[i][j].draw(183, 109, 85, 0, 0, 0);
                    }
                    else {
                        this.cells[i][j].draw(255, 255, 255, 0, 0, 0);
                    }
                }
            } 
        }
    }  
} 
