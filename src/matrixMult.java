import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

// is importing all of this better than doing a wildcard import?

public class matrixMult {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // used to store matrix names
        String file1 = "", file2 = "";

        // this part is for command line
        if (args.length == 2) {
            file1 = args[0];
            file2 = args[1];
        
        // this part is for keyboard input
        } else {
            System.out.print("Please input the file name of the first matrix:");
            file1 = scanner.nextLine();
            System.out.print("Please input the file name of the first matrix:");
            file2 = scanner.nextLine();
        }

        scanner.close();

        // change variable name pls
        int[][] matrixA = readMatrixFile(file1);
        int[][] matrixB = readMatrixFile(file2);

        // change variable name
        int[][] resultMatrix = multMatrices(matrixA, matrixB);

        // uses the new matrix and the write function to make the next text file
        writeMatrixToFile("matrix3.txt", resultMatrix);
        System.out.println("Your new matrix has been saved to 'matrix3.txt' ");
    }

    // reads the matrix file line by line and creates it into a readable matrix
    public static int[][] readMatrixFile(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            List<int[]> matrixList = new ArrayList<>();
    
            // continues reading as long as there is another line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim(); //trim removes extra spaces
                if (line.isEmpty()) continue;
                
                // splits into individual strings
                String[] numbers = line.split("\\s+");

                // converts each string to an int and puts it in the row array
                int[] row = new int[numbers.length];
                for (int i = 0; i < numbers.length; i++) {
                    row[i] = Integer.parseInt(numbers[i]);
                }
                
                // adds the created row to the list
                matrixList.add(row);
            }
            
            // turns the list into a 2d array
            return matrixList.toArray(new int[0][]); 
        } catch (FileNotFoundException e) {
            System.out.println("Error: File " + filename + " not found.");
        } catch (Exception e) {
            System.out.println("Error: Invalid matrix format in " + filename);
        }
        return null;
    }

    public static int[][] multMatrices(int[][] A, int[][] B) {
        // dimensions of the matrices
        int rowsA = A.length;
        int colsA = A[0].length;
        int colsB = B[0].length;
        int[][] C = new int[rowsA][colsB];

        // creates the new c matrix
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    // why did i have to add the try
    public static void writeMatrixToFile(String filename, int[][] matrix) {
        try (PrintWriter writer = new PrintWriter(new File(filename))) {    
            for (int[] row : matrix) {
                for (int num : row) {
                    writer.print(num + " ");
                }
                writer.println();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filename);
        }
    } 
}
