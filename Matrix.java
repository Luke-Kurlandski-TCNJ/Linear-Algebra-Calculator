
/**
 * Matrix Class
 */
import java.util.Scanner;
public class Matrix
{
    private double[][] matrix;
    static Scanner scan = new Scanner(System.in);
    
    //Constructs a Matrix object based upon the depth and width
    public Matrix(int depth, int width)
    {
        matrix = new double[depth][width];
    }
    
    //Fills a matrix with numbers
    public void populate() {
        System.out.println("Populate the matrix.");
        for (int i=0 ; i<this.matrix.length ; i++) {
            System.out.println("Enter the values for row " + (i+1));
            for (int j=0 ; j<this.matrix[0].length ; j++) {
                this.matrix[i][j] = scan.nextDouble();
            }
        }
    }
    
    //Prints the matrix
    public void print() {
        for (int i=0 ; i<this.matrix.length ; i++) {
            for (int j=0 ; j<this.matrix[0].length ; j++) {
                System.out.print(this.matrix[i][j] + "  ");
            }
            System.out.println();
        }
    }
    
    //Gives the depth of a matrix
    public int getDepth() {
        return this.matrix.length;
    }
    //Gives the width of a matrix
    public int getWidth() {
        return this.matrix[0].length;
    }
    //Converts a Matrix obect to an array
    //Returns double[][]
    public double[][] toArray() {
        double[][] matrix2 = new double[this.getDepth()][this.getWidth()];
        for (int i=0 ; i<this.getDepth() ; i++) {
            for (int j=0 ; j<this.getWidth() ; j++) {
                matrix2[i][j] = this.matrix[i][j];
            }
        }
        return matrix2;
    }
    //Converts an array into a Matrix Object
    //Returns Matrix
    public Matrix toObject(double [][] matrixArray) {
        Matrix matrixObject = new Matrix(matrixArray.length, matrixArray[0].length);
        matrixObject.matrix = matrixArray.clone();
        return matrixObject;
    }
    
    //Adds a second matrix to this.matrix
    //Returns (this.matrix+matrix2)
    public Matrix add(Matrix Matrix2) {
        //Creates a new double[][] out of Matrix2 object
        double[][] matrix2 = Matrix2.toArray().clone();
        //Checks if the matricies have the same dimentions
        if ((this.matrix.length != matrix2.length) || (this.matrix[0].length != matrix2[0].length)) {
            System.out.println("Error. These matricies have different dimentions.");
            return this;
        }
        //Creates a new array to be returned
        double[][] newMatrix = new double[this.matrix.length][this.matrix[0].length];
        //Adds every element in matrix2 to this.matrix
        for (int i=0 ; i<this.matrix.length ; i++) {
            for (int j=0 ; j<this.matrix[0].length ; j++) {
                newMatrix[i][j] = this.toArray()[i][j] + matrix2[i][j];
            }
        }
        return toObject(newMatrix);
    }
    
    //Subtracts a second matrix from this.matrix
    //Returns (this.matrix-matrix2)
    public Matrix subtract(Matrix Matrix2) {
        //Creates a new double[][] out of Matrix2 object
        double[][] matrix2 = Matrix2.toArray().clone();
        //Checks if the matricies have the same dimentions
        //If they are not, returns this
        if ((this.matrix.length != matrix2.length) || (this.matrix[0].length != matrix2[0].length)) {
            System.out.println("Error. These matricies have different dimentions.");
            return this;
        }
        //Creates a new array to be returned
        double[][] newMatrix = new double[this.matrix.length][this.matrix[0].length];
        //Subtracts every element in matrix2 to this.matrix
        for (int i=0 ; i<this.matrix.length ; i++) {
            for (int j=0 ; j<this.matrix[0].length ; j++) {
                newMatrix[i][j] = this.toArray()[i][j] - matrix2[i][j];
            }
        }
        return toObject(newMatrix);
    }
    
    //Multplies a second matrix to this.matrix
    //Calls subMultiply()
    //Returns (this.matrix * matrix2)
    public Matrix multiply(Matrix Matrix2) {
        //Creates a new double[][] out of Matrix2 object
        double[][] matrix2 = Matrix2.toArray().clone();
        //Checks to see if the matricies can be multiplied
        //If they cannot be, returns this.matrix
        if (this.matrix[0].length != matrix2.length) {
            System.out.println("Error. These matricies have incompatable dimentions for multiplication.");
            return this;
        }
        //Creates and fills a new matrix to be returned
        double[][] newMatrix = new double[this.matrix.length][matrix2[0].length];
        for (int i=0 ; i<this.matrix.length ; i++) {
            for (int j=0 ; j<matrix2[0].length ; j++) {
                newMatrix[i][j] = subMultiply(this.matrix, matrix2, i, j);
            }
        }
        return toObject(newMatrix);
    }
    //Mutiplies the row and collumn of two matricies
    //Returns the sum of the products of the rows and collumns
    public double subMultiply(double M1[][], double M2[][], int startRow, int startCol) {
        double result = 0;
        for (int i=0 ; i<M1[0].length ; i++) {
            result += M1[startRow][i] * M2[i][startCol];
        }
        return result;
    }
    
    //Transposes this.matrix
    //Returns (this.matrix^T)
    public Matrix transpose() {
        //Creates a new matrix with the reverse dimentions of this.matrix
        double[][] newMatrix = new double[this.matrix[0].length][this.matrix.length];
        for(int i=0 ; i<this.matrix.length ; i++) {
            for(int j=0 ; j<this.matrix[0].length ; j++) {
                newMatrix[j][i] = this.matrix[i][j];
            }
        }
        return toObject(newMatrix);
    }
    
    //Eliminate this.matrix to Row Echalon Form
    //Calls subEliminate()
    //Returns red(this.matrix)
    public Matrix eliminate() {
        //Creates a new double[][] out of Matrix2 object
        double[][] thisMatrix = this.toArray().clone();
        double multiplier;
        //Loops through the matrix, calls subEliminate()
        for (int i=0 ; i<this.matrix[0].length ; i++) {
            for (int j=i ; j<this.matrix.length-1 ; j++) {
                if (thisMatrix[i][i] != 0) {
                    multiplier = (thisMatrix[j+1][i] / thisMatrix[i][i]);
                    subEliminate(thisMatrix, multiplier, j, i);
                }               
            }
        }
        return toObject(thisMatrix);
    }
    //Supprt method for eliminate()
    //Does the actual subtracting and reassignment of values when eliminating
    private void subEliminate(double [][] thisMatrix, double multiplier, int rowModded, int rowMult) {
        for (int j=0 ; j<thisMatrix[0].length ; j++) {
            thisMatrix[rowModded+1][j] = thisMatrix[rowModded+1][j] - (multiplier*thisMatrix[rowMult][j]);
        }
    }
    
    //Eliminate this.matrix to Reduced Row Echalon Form
    //Calls eliminate(), subreduce(), and shuffleRows()
    //Returns rref(this.matrix)
    public Matrix eliminateReduced() {
        //Creates a new double[][] out of a reduced Matrix object
        double[][] thisMatrix = this.eliminate().toArray().clone();
        double multiplier;
        int startI = thisMatrix[0].length-1;
        int startJ = 1;
        //Finds non zero digit in bottom right of matrix. Signals which rows to begin eliminating.
        for (int i=thisMatrix.length-1 ; i>-1 ; i--) {
            if (thisMatrix[i][thisMatrix[0].length-1] != 0) {
                startJ = i;
                break;
            }
        }
        //Loops through the matrix, calls subReduce()
        for (int i= startI; i>-1 ; i--) { //refers to columns
            for (int j= startJ-startI+i; j>0 ; j-- ) { //refers to rows 
                if (thisMatrix[startJ-startI+i][i] != 0) {
                    multiplier = thisMatrix[j-1][i] / thisMatrix[startJ-startI+i][i];
                    subReduce(thisMatrix, multiplier, j-1, startJ-startI+i);
                }
            }
        }
        //shuffleRows(thisMatrix);
        return toObject(thisMatrix);
    }
    //Supprt method for eliminateReduced()
    //Does the actual subtracting and reassignment of values when eliminating
    private void subReduce(double[][] thisMatrix, double multiplier, int rowModded, int rowMult) {
        for (int j=thisMatrix[0].length-1 ; j>-1 ; j--) {
            thisMatrix[rowModded][j] = thisMatrix[rowModded][j] - (multiplier*thisMatrix[rowMult][j]);
        }
    }
    /** FIX_ME (method is not functional, causes an infinite loop) */
    //Support Method for eliminateReduced()
    //Calls sendRow()
    //Moves the rows of 0s to the bottom in a matrix
    public /**FIX_ME private void*/ Matrix shuffleRows(/**FIX_ME double[][] thisMatrix*/) { 
    	//Fix Me 
    	double[][] thisMatrix = this.matrix.clone();
    	int countCalls = 0;
    	//Fix Me ^
        //Cycles through the matrix rows or columns (which ever is smaller)
        for (int i=0 ; (i<thisMatrix.length) || (i<thisMatrix[0].length) /**FIX_ME*/ ; i++) {
            //Locates "pivots" of 0
            if (thisMatrix[i][i] == 0) {
                //Figures out what row to send the to, based upon number of 0s in the row
                int sendTo = i;
                for (int j=i+1 ; j<thisMatrix[0].length ; j++) {
                    if (thisMatrix[i][j]==0) {
                        sendTo = sendTo+1;
                    }
                    //If sendRow == lastRow, breaks the loop
                    if (sendTo==thisMatrix.length-1) {
                    	break;
                    }
                }
                //Moves the row to the correct location
                sendRow(i, sendTo, thisMatrix);
                /*FIX_ME*/ countCalls++; System.out.println(countCalls);
                
                //Decrements i, must re-analyze row i because row i has changed
                i = i-1;
            }
        }
        
        //For testing
        //System.out.println("Why");
        return toObject(thisMatrix);
    }
    //Support Method for shuffleRows
    //Does the actual "moving" of the rows
    private void sendRow(int send, int sendTo, double[][] thisMatrix) {
        double [] tempRow = new double[thisMatrix[0].length];
        
        if (sendTo!=thisMatrix.length-1) {
        	//Move sendTo --> sendTo+1	
        	for (int i=sendTo ; i<thisMatrix.length-2 ; i++) {
        		tempRow = thisMatrix[i+1].clone();
        		thisMatrix[i+1] = thisMatrix[i].clone();
        		thisMatrix[i+2] = tempRow.clone();
        	}
        	//Move send --> sendTo
            thisMatrix[sendTo] = thisMatrix[send].clone();
            //Move lastRow --> send
            thisMatrix[send] = thisMatrix[thisMatrix.length-1].clone();
        }
        if (sendTo==thisMatrix.length) {
        	//Move sendTo --> send
        	//Move send -->sendTo
        	tempRow = thisMatrix[sendTo].clone();
        	thisMatrix[sendTo] = thisMatrix[send].clone();
        	thisMatrix[send]=tempRow.clone();
        }
        
    }
    
    
    
    //Calculate the nullspace of this.matrix
    //Calls eliminate()
    //Returns N(this.matrix)
    public Matrix nullspace() {
        //Creates a new double[][] out of Matrix2 object
        double[][] thisMatrix = this.toArray().clone();
        
        
        return toObject(thisMatrix);
    }
}
