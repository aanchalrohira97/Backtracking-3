// edge case: 1 queen then 1st block, for 9 queens -> different positions
// constraints: queen should not be present in any of the 8 directions

/*
-> since the queen can move in all the 8 directions, we have to make sure there is no coinciding queen in that position
-> simple approach is to place the queen at one position in the 1st row and then place the other queen in the 2nd row
-> check there is not queen in that direction
-> if there is, then backtrack your steps to that row and place the queen in the next block 
-> check for that position the previous queens are not in attack direction

-> challenge is the implementation

 TC: O(n!) -> where n is time taken to find if it is safe to put queen 
 SC: O(n) -> maxiumum depth of call stack
*/

class Solution {

  List<List<String>> results = new ArrayList<>();

  public List<List<String>> solveNQueens(int n) {
    int[][] board = new int[n][n];
    boolean res = placequeens(board, 0, n);
    return results;
  }

  public boolean placequeens(int[][] board, int r, int n) {
    //base case
    if (r == n) {
      List<String> temp = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        String a = new String();
        for (int j = 0; j < n; j++) {
          if (board[i][j] == 1) {
            a += 'Q';
          } else {
            a += '.';
          }
        }
        temp.add(a);
      }
      results.add(temp);
      return false;
    }

    //recussive case
    for (int i = 0; i < n; i++) {
      if (isSafe(board, r, i, n)) {
        board[r][i] = 1;
        if (placequeens(board, r + 1, n)) {
          return true;
        }
      }
      board[r][i] = 0;
    }
    return false;
  }

  public boolean isSafe(int board[][], int i, int j, int n) {
    for (int r = 0; r < i; r++) {
      if (board[r][j] == 1) {
        return false;
      }
    }

    //left diagonal
    int x = i - 1;
    int y = j - 1;

    while (x >= 0 && y >= 0) {
      if (board[x][y] == 1) {
        return false;
      }
      x--;
      y--;
    }

    //right diagonal
    x = i - 1;
    y = j + 1;

    while (x >= 0 && y < n) {
      if (board[x][y] == 1) return false;

      x--;
      y++;
    }

    return true;
  }
}
