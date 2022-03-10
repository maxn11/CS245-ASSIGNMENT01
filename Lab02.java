//Maximus Nie
//3/10/2022
//CS245 Assignment1: Nonogram

//i couldn't complete it
public class Lab02 {
	
	public static boolean[][] nonogram = new boolean[9][9];
	public static int[][] nonoColumns = {{0,2}, {2,1}, {0,4}, {0,3}, {0,1}};
	public static int[][] nonoRows = {{0,4}, {0,4}, {0,3}, {0,1}, {0,1}};
	
	//nonogram method solves the puzzle with the given two parameters
	//@param double array of integers that represent the columns
	//@param double array of integers that represent the rows
	//@returns double boolean array of solution
	//try to use recursive backtracking
	public static boolean[][] solveNonogram(int[][] columns, int[][] rows) {
		//[TODO: your solution]
		if(solver(0, 0, 0)) {
			return nonogram;
		} else {
			System.out.println("Could not solve nonogram.");
			return nonogram;
		}
	}
	
	public static boolean solver(int row, int col, int index) {
		if(row == nonoRows.length) {
			return true;
		}
		if(row == 0 && col == nonoColumns.length) {
			col = 0;
			row += 1;
		}
		for(int i = col; i < nonoColumns.length; i++) {
			if(isValid(nonoRows[row], nonoColumns[col], index)) {
				if(index == 1) {
					if(solver(row + 1, 0, 0)) {
						return true;
					} else {
						if(solver(row, col + 1, 0)) {
							return true;
						} else {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	public static boolean columnCheck(int[] column, int indexToPaint, boolean space) {
		if(column[0] > 0 || column[1] > 0) {
			if(column[0] == 0 && space && nonogram[indexToPaint - 1][0] == true) {
				return false;
			} else if((nonogram[indexToPaint - 1][0] || nonogram[indexToPaint + 1][0]) && !space) {
				return true;
			} else if(column[0] > 0 || column[1] > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public static boolean rowCheck(int[] row, int indexToPaint, boolean space) {
		if(row[0] > 0 || row[1] > 0) {
			if(row[0] == 0 && space && nonogram[0][indexToPaint - 1] == true) {
				return false;
			} else if((nonogram[0][indexToPaint - 1] || nonogram[0][indexToPaint + 1]) && !space) {
				return true;
			} else if(row[0] > 0 || row[1] > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public static boolean isValid(int[] row, int[] column, int index) {
		boolean rowSpace = false, colSpace = false;
		if(column[0] != 0) {
			colSpace = true;
		}
		if(row[0] != 0) {
			rowSpace = true;
		}
		for(int i = 0; i < row[index]; i++) {
			if(!columnCheck(column, i, colSpace) && !rowCheck(row, i, rowSpace)) {
				return false;
			}
		}
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
		
		solveNonogram(nonoColumns, nonoRows);
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(nonogram[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	 
}