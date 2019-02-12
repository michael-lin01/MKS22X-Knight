public class KnightBoard{

  public int[][] board;

  public KnightBoard(int startingRows, int startingCols){
    board = new int[startingRows][startingCols];
    for(int r = 0; r < startingRows; r++){
      for(int c = 0; c < startingCols;c++){
        board[r][c]=0;
      }
    }
  }

  public String toString(){
    String ans = "";
    for(int r = 0; r < board.length; r++){
      for(int c = 0; c < board[r].length;c++){
        if(board[r][c]<10) ans += " "+board[r][c]+" ";
        else ans+= board[r][c]+" ";
      }
      ans+="\n";
    }
    return ans;
  }

  public static void main
}
