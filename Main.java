
/**
 * Main Class
 */
import java.util.Scanner;
public class Main
{
    static Scanner scan = new Scanner(System.in);
    public static void main (String args[]) {
        Matrix M1 = createMatrix(1);
        printOptions(M1);
    }
    
    public static void printOptions(Matrix M1) {
        System.out.println("What do you want to do?");
        System.out.println("0: Exit\n1: Add a Matrix\n2: Subtract a Matrix\n3: Multiply a Matrix" + 
        "\n4: Transpose the Matrix\n5: Row Echalon the Matrix\n6: Reduced Row Echalon the Matrix" + 
        		"\n7: Switch the Matrix Rows (temporary)");
        int choice = scan.nextInt();
        System.out.println("_________________________________________________");
        handleOptions(choice, M1);
    }
    
    public static int printOptions2() {
        System.out.println("Do you want to work with M1, M2 " + 
        "(if you entered a second matrix), M3, or a new Matrix?");
        System.out.println("Enter 1, 2, 3, or 4, respectivly. Enter 0 to exit.");
        int choice = scan.nextInt();
        line();
        return choice;
    }
    
    public static void handleOptions(int choice, Matrix M1) {
        Matrix M2 = null;
        Matrix M3 = null;
        switch (choice) {
            case 0:
                System.out.println("System terminated");
                System.exit(0);
                break;
            case 1:
                M2 = createMatrix(2);
                M3 = M1.add(M2);
                M3.print();
                break;
            case 2:
                M2 = createMatrix(2);
                M3 = M1.subtract(M2);
                M3.print();
                break;
            case 3:
                M2 = createMatrix(2);
                M3 = M1.multiply(M2);
                M3.print();
                break;
            case 4:
                M3 = M1.transpose();
                M3.print();
                break;
            case 5:
                M3 = M1.eliminate();
                M3.print();
                break;
            case 6:
                M3 = M1.eliminateReduced();
                M3.print();
                break;
            case 7: 	
                M3 = M1.shuffleRows();
                M3.print();
                break;
            default:
                System.out.println("Not an Option");
                line();
                break;
        }
        //Recalls printOptions()
        //Uses the matrix the user chooses to work with in printOptions2() 
        switch (printOptions2()) {
            case 0:
                System.exit(0);
                break;
            case 1:
                printOptions(M1);
                break;
            case 2:
                printOptions(M2);
                break;
            case 3:
                printOptions(M3);
                break;
            case 4:
                printOptions(createMatrix(1));
                break;
            default:
                System.out.println("Not an option.");
                printOptions2();
        }
    }
    
    public static Matrix createMatrix(int num) {
        int depth = -1;
        int width = -1;
        System.out.println("Enter the depth, followed by the width, of Matrix " + num + " (M" + num + ")");
        depth = scan.nextInt();
        width = scan.nextInt();
        Matrix M = new Matrix(depth, width);
        M.populate();
        line();
        return M;
    }
    
    public static void line() {
        System.out.println("_____________________________________________________________");
    }
    
    public static void choiceAdd() {
        Matrix M1 = createMatrix(1);
        Matrix M2 = createMatrix(2);
        System.out.println("Result: M1 + M2 = ");
        M1.add(M2).print();
        line();
        //printOptions();
    }
    
    public static void choiceSubtract() {
        Matrix M1 = createMatrix(1);
        Matrix M2 = createMatrix(2);
        System.out.println("Result: M1 - M2 = ");
        M1.subtract(M2).print();
        line();
        //printOptions();
    }
    
    public static void choiceMultiply() {
        Matrix M1 = createMatrix(1);
        Matrix M2 = createMatrix(2);
        System.out.println("Result: M1 * M2 = ");
        M1.multiply(M2).print();
        line();
        //printOptions();
    }
    
    public static void choiceTranspose() {
        Matrix M1 = createMatrix(1);
        System.out.println("Result: M1 ^ T = ");
        M1.transpose().print();
        line();
        //printOptions();
    }
    
    public static void choiceEliminate() {
        Matrix M1 = createMatrix(1);
        System.out.println("Result: ref(M1) = ");
        M1.eliminate().print();
        line();
        //printOptions();
    }
    
    public static void choiceReducedRow() {
        Matrix M1 = createMatrix(1);
        System.out.println("Result: rref(M1) = ");
        M1.eliminateReduced().print();
        line();
        //printOptions();
    }
}
