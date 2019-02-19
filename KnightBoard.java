public class KnightBoard{

  public int[][] board;
  private int[][] moves = new int[][]{{-2,-1},{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2}};

  public KnightBoard(int startingRows, int startingCols){
    if(startingRows<0||startingCols<0) throw new IllegalArgumentException();
    board = new int[startingRows][startingCols];
    for(int r = 0; r < startingRows; r++){
      for(int c = 0; c < startingCols;c++){
        board[r][c]=0;
      }
    }
  }

  private boolean addKnight(int row, int col, int level){
    if(row<0 || row>=board.length || col < 0 || col >= board[row].length || board[row][col]!=0) {
      return false;
    }
    board[row][col] = level;
    return true;
  }

  private boolean removeKnight(int row, int col){
    board[row][col]=0;
    return true;
  }


  public boolean solve(int startingRow, int startingCol){
    if(board[0][0]!=0) throw new IllegalStateException();
    if(startingRow<0||startingRow>=board.length||
       startingCol<0||startingCol>=board[0].length) throw new IllegalArgumentException();
    addKnight(startingRow, startingCol, 1);
    return solveH(startingRow,startingCol,2);
  }

  private boolean solveH(int row, int col, int level){
    if(level>board.length*board[0].length) {
      //System.out.println(this);
      return true;
    }
    for(int i = 0; i < moves.length;i++){
      if(addKnight(row+moves[i][0],col+moves[i][1],level)){
        if (solveH(row+moves[i][0],col+moves[i][1],level+1)){
          return true;
        }
        removeKnight(row+moves[i][0],col+moves[i][1]);
      }

    }
    //System.out.println(this);
    return false;
  }

  public int count(int startingRow, int startingCol){
    if(board[0][0]!=0) throw new IllegalStateException();
    if(startingRow<0||startingRow>=board.length||
       startingCol<0||startingCol>=board[0].length) throw new IllegalArgumentException();
    addKnight(startingRow, startingCol, 1);
    return countH(startingRow, startingCol, 2);
  }

  private int countH(int row, int col, int level){
    if(level>board.length*board[0].length) {
      //System.out.println(this);
      return 1;
    }
    int count = 0;
    for(int i = 0;i < moves.length;i++){
      if(addKnight(row+moves[i][0],col+moves[i][1],level)){
        count+= countH(row+moves[i][0],col+moves[i][1],level+1);
        removeKnight(row+moves[i][0],col+moves[i][1]);
      }
    }
    return count;
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
    /*
    KnightBoard b = new KnightBoard(4,4);
    b.board[2][2]=12;
    System.out.println(b);
    */
    KnightBoard b = new KnightBoard(5,5);
    System.out.println(b.solve(0,0));
    System.out.println(b);
    b = new KnightBoard(5,5);
    System.out.println(b.count(0,2));
  }
}
