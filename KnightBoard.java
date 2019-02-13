public class KnightBoard{

  public int[][] board;

  public KnightBoard(int startingRows, int startingCols){
    if(startingRows<0||startingCols<0) throw new IllegalArgumentException();
    board = new int[startingRows][startingCols];
    for(int r = 0; r < startingRows; r++){
      for(int c = 0; c < startingCols;c++){
        board[r][c]=0;
      }
    }
  }

  public boolean solve(int startingRow, int startingCol){
    if(board[0][0]!=0) throw new IllegalStateException();
    if(startingRow<0||startingRow>=board.length||
       startingCol<0||startingCol>board[0].length) throw new IllegalArgumentException();
    return solveH(startingRow,startingCol,0);
  }

  private boolean solveH(int row, int col, int level){
    if(level>board.length*board[0].length) return true;
    
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

  public static void main(String args[]){
    KnightBoard b = new KnightBoard(4,4);
    b.board[2][2]=12;
    System.out.println(b);
  }
}
