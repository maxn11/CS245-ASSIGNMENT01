//Maximus Nie
//3/10/2022
//CS245 Assignment1: Nonogram

//i couldn't complete it
public class Lab02 {
	
	//nonogram board
	public static boolean[][] nonogram = new boolean[9][9];
	//shaded column nums
	public static int[][] nonoColumns = {{0,2}, {2,1}, {0,4}, {0,3}, {0,1}};
	//shaded row nums
	public static int[][] nonoRows = {{0,4}, {0,4}, {0,3}, {0,1}, {0,1}};
	
	//nonogram method solves the puzzle with the given two parameters
	//@param double array of integers that represent the columns
	//@param double array of integers that represent the rows
	//@returns double boolean array of solution
	public static boolean[][] solveNonogram(int[][] columns, int[][] rows) {
		//[TODO: your solution]
		if(solver(0, 0, 0)) {
			return nonogram;
		} else {
			System.out.println("Could not solve nonogram.");
			return nonogram;
		}
	}
	
	//Try using recursive backtracking
	//@param int row which is the row position
	//@param int col which is the column position
	//@param int index which is the current position
	//@returns true if the board is solved or if the current arrangement works, returns
	//false if current arrangement doesn't work or board can't be solved
	//returning false leads to backtracking
	public static boolean solver(int row, int col, int index) {
		//base case
		//end reached and solved
		if(row == nonoRows.length) {
			return true;
		}
		//traverse to the next row once the end of the previous row is reached
		if(row == 0 && col == nonoColumns.length) {
			col = 0;
			row += 1;
		}
		//iterate through columns to test each spot
		for(int i = col; i < nonoColumns.length; i++) {
			if(isValid(nonoRows[row], nonoColumns[col], index)) {
				if(index == 1) {
					if(solver(row + 1, 0, 0)) {
						//set to "shaded"
						nonogram[row][col] = true;
						return true;
					} else {
						if(solver(row, col + 1, 0)) {
							//set to "shaded"
							nonogram[row][col] = true;
							return true;
						} else {
							//set back to "unshaded"
							//backtracking
							nonogram[row][col] = false;
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	//@param int array column which is the sub array of nonoColumns
	//@param int shadeIndex position to shade in (make false)
	//@param boolean space which says if there is a space between the column shading clues
	//@returns true if the column is safe to continue and false otherwise
	public static boolean columnCheck(int[] column, int shadeIndex, boolean space) {
		if(column[0] > 0 || column[1] > 0) {
			if(column[0] == 0 && space && nonogram[shadeIndex - 1][0] == true) {
				return false;
			} else if((nonogram[shadeIndex - 1][0] || nonogram[shadeIndex + 1][0]) && !space) {
				//check for space
				return true;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
	
	//flip columnCheck
	//@param int array row which is the sub array of nonoRows
	//@param int shadeIndex position to shade in (make false)
	//@param boolean space which says if there is a space between the row shading clues
	//@returns true if the column is safe to continue and false otherwise
	public static boolean rowCheck(int[] row, int shadeIndex, boolean space) {
		//check if sub array has values
		if(row[0] > 0 || row[1] > 0) {
			if(row[0] == 0 && space && nonogram[0][shadeIndex - 1] == true) {
				return false;
			} else if((nonogram[0][shadeIndex - 1] || nonogram[0][shadeIndex + 1]) && !space) {
				//check for a space
				return true;
			} else {
				//none of the above
				return true;
			}
		} else {
			return false;
		}
	}
	
	//like sudoku program, check if spot is valid
	//@param int array row which is the sub array of nonoRows
	//@param int array column which is a sub array of nonoColumns
	//@param int index for current position
	//@returns true if the current arrangement is valid, false otherwise
	public static boolean isValid(int[] row, int[] column, int index) {
		//set spaces to false
		boolean rowSpace = false, colSpace = false;
		//if the first spot in the sub array isn't 0, then there is a space
		if(column[0] != 0) {
			//tell function that there is a space
			colSpace = true;
		}
		if(row[0] != 0) {
			rowSpace = true;
		}
		//run through column and row checks
		for(int i = 0; i < row[index]; i++) {
			if(!columnCheck(column, i, colSpace) && !rowCheck(row, i, rowSpace)) {
				//not valid
				return false;
			}
		}
		//valid
		return true;
	}
	
	public static void main(String[] args){
		//test your code
		//fill all false
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				nonogram[i][j] = false;
			}
		}
		
		//call function
		solveNonogram(nonoColumns, nonoRows);
		
		//print out
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(nonogram[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	 
}