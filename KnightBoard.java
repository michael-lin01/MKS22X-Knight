import java.util.*;

public class KnightBoard{

  private class Tile implements Comparable<Tile>{
    public int r,c;
    public Tile(int r, int c){
      this.r = r;
      this.c = c;
    }

    public int compareTo(Tile other){
      return boardMoves[r][c]-boardMoves[other.r][other.c];
    }
  }

  public int[][] board;
  private int[][] boardMoves;
  private int[][] moves = new int[][]{{-2,-1},{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2}};

  public KnightBoard(int startingRows, int startingCols){
    if(startingRows<0||startingCols<0) throw new IllegalArgumentException();
    board = new int[startingRows][startingCols];
    boardMoves = new int[startingRows][startingCols];
    for(int r = 0; r < startingRows; r++){
      for(int c = 0; c < startingCols;c++){
        board[r][c]=0;
        boardMoves[r][c]=0;
        for(int i = 0; i<moves.length;i++){
          if(r+moves[i][0]>=0 && r+moves[i][0]<board.length && c+moves[i][1]>=0 && c+moves[i][1] < board.length) boardMoves[r][c]++;
        }
      }
    }
  }

  private boolean addKnight(int row, int col, int level){
    if(row<0 || row>=board.length || col < 0 || col >= board[row].length || board[row][col]!=0) {
      return false;
    }
    board[row][col] = level;
    for(int i = 0; i < moves.length;i++){
      if(isAMove(row+moves[i][0],col+moves[i][1])){
        boardMoves[row][col] = 0;
        boardMoves[row+moves[i][0]][col+moves[i][1]]--;
      }
    }
    return true;
  }

  private boolean removeKnight(int row, int col){
    board[row][col]=0;
    for(int i = 0; i < moves.length;i++){
      if(isAMove(row+moves[i][0],col+moves[i][1])&&){
        boardMoves[row][col]++;
        boardMoves[row+moves[i][0]][col+moves[i][1]]++;
      }
    }
    return true;
  }

  private boolean isAMove(int r, int c){
    return r>=0&&r<board.length&&c>=0&&c<board[0].length;
  }

  public boolean solve(int startingRow, int startingCol){
    if(board[0][0]!=0) throw new IllegalStateException();
    if(startingRow<0||startingRow>=board.length||
       startingCol<0||startingCol>=board[0].length) throw new IllegalArgumentException();
    addKnight(startingRow, startingCol, 1);
    //return solveH(startingRow,startingCol,2);
    return solveOpt(startingRow,startingCol,2);
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

  public boolean solveOpt(int row, int col, int level){
    if(level>board.length*board[0].length) {
      //System.out.println(this);
      return true;
    }
    List<Tile> tiles = new ArrayList<Tile>();
    for(int i = 0;i < moves.length;i++){
      Tile t = new Tile(moves[i][0],moves[i][1]);
      if (isAMove(t.r,t.c)&&boardMoves[t.r][t.c]!=-1) tiles.add(t);
    }
    Collections.sort(tiles);
    for(int i = 0;i < tiles.size();i++){
      if (addKnight(row+tiles.get(i).r,col+tiles.get(i).c,level)){
        if(solveOpt(row+tiles.get(i).r,col+tiles.get(i).c,level+1)){
          return true;
        }
        removeKnight(row+tiles.get(i).r,col+tiles.get(i).c);
      }
      
    }
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

  public String toStringMoves(){
    String ans = "";
    for(int r = 0; r < boardMoves.length; r++){
      for(int c = 0; c < boardMoves[r].length;c++){
        if(boardMoves[r][c]<10) ans += " "+boardMoves[r][c]+" ";
        else ans+= boardMoves[r][c]+" ";
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
    System.out.println(b.toStringMoves());
    //b = new KnightBoard(5,5);
    //System.out.println(b.count(0,2));
  }
}
