/**
* Name: Yuvaraj Gambhir
* Pennkey: 12279760
* Execution: n/a cell class
*
* Description: creates the cell objects that make up the board
**/
public class Cell {
    
    private boolean isOriginalValue;
    private char value;
    private double posX, posY;
    public static double halfWidth = 0.0555555555555;
    public int toggleCellSwitch = 0;
    
    public Cell(char value, double posX, double posY) {
        this.value = value;
        this.posX = posX;
        this.posY = posY;
        
        this.isOriginalValue = (value != ' ');
        
    }
    
    /**
    * Inputs: rgb colors for fill and border
    * Outputs: none
    * Description: handles drawing a cells and the colors
    */
    public void draw(int fillred, int fillgreen, int fillblue, int borderred, int bordergreen, int borderblue) {
        PennDraw.setPenColor(fillred, fillgreen, fillblue);
        
        PennDraw.filledSquare(this.posX, this.posY, halfWidth);
        
        PennDraw.setPenColor(borderred, bordergreen, borderblue);
        
        PennDraw.setPenRadius(0.004);
        
        PennDraw.square(this.posX, this.posY, halfWidth);
        
        PennDraw.setFontSize(25);
        PennDraw.setPenColor(0, 0, 0);
        PennDraw.text(this.posX, this.posY, "" + this.value);
        
    }
    
    /**
    * Inputs: none
    * Outputs: char
    * Description: getter method for value in cell
    */
    public char getValue() {
        return this.value;
    }
    
    /**
    * Inputs: value
    * Outputs: none
    * Description: setter method for value
    */
    public void setValue(char value) {
        if (value >= '1' && value <= '9') {
            this.value = value;
        }
        
    }
    
    /**
    * Inputs: none
    * Outputs: double
    * Description: getter method for cell location on canvas
    */
    public double getPosX() {
        return this.posX;
    }
    
 /**
    * Inputs: none
    * Outputs: double
    * Description: getter method for cell location on canvas
    */
    public double getPosY() {
        return this.posY;
    }
    
    /**
    * Inputs: none
    * Outputs: boolean
    * Description: checks if the cell contains an original input value from file
    */
    public boolean getIsOriginal() {
        return this.isOriginalValue;
    }
    


    
}
