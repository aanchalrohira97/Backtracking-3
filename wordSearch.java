// edge case: board is null
// constraints: lowercase and uppercase have to match, since problem says the board consists of uppercase and lowercase alphabets
// yes

/*

-> can be done both with bfs and dfs
-> the first obvious solution is that we do dfs and backtrack if we cannot find the word
-> backtrack to the alphabet from where we match 
-> if all direction from that alphabet fail -> go back further more and then go into the other alphabet match from there

-> the question is do we really need backtracking? 
-> since we only really need to find if the combination exists in the grid
-> and with dfs we go back to the previous call in the stack for recurssion
-> the following example shows why we need backtracking 

 A B C C E
 S F C E D
 A D E F N

 and we want to find: BCCEFNDEC 
 
-> where we find first matching character, we do dfs from there
-> and we have a directions array 
-> in dfs when we visit a node, we usually mark it as visited
-> when we are backtracking from a node that we did not utilize, we want to backtrack and mark it as not visited
-> because in another path, it is possible we find what we are looking for when visiting that node 

TC: O(m * n * 4^l) 
SC: O(l) -> length of the word
*/

class Solution {

  int[][] dirs;
  int m;
  int n;

  public boolean exist(char[][] board, String word) {
    //null
    if (board == null) return false;
    //only need 4 directions for this problem
    dirs = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    m = board.length;
    n = board[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] == word.charAt(0)) {
          //with connected component we were able to find the complete word
          if (backtrack(board, word, 0, i, j)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private boolean backtrack(
    char[][] board,
    String word,
    int idx,
    int i,
    int j
  ) {
    //base
    if (idx == word.length()) return true;
    if (
      i < 0 ||
      j < 0 ||
      i == board.length ||
      j == board[0].length ||
      board[i][j] == '#'
    ) return false;

    //logic
    if (board[i][j] == word.charAt(idx)) {
      //action
      board[i][j] = '#';
      //recurse in all directions
      for (int[] dir : dirs) {
        int r = i + dir[0];
        int c = j + dir[1];
        //if neighbor onward i am able to find the rest of the word
        if (backtrack(board, word, idx + 1, r, c)) return true;
      }
      //backtrack
      board[i][j] = word.charAt(idx);
    }
    return false;
  }
}
