class Board{
	private int row;
	private int[] queenPos;
	private int ansCount;
	private int scale;
	public Board(int n){
		scale = n;
		queenPos = new int[n+1];
		for(int i=0;i<=n;i++){
			queenPos[i]=0;
		}
		ansCount=0;
		row = 1;
	}
	public int getAns(){
		return ansCount;
	}
	public void foundAns(){
		ansCount++;
	}
	public int queenPosOfRow(int row){
		return queenPos[row];
	}
	public void queenReset(int row){
		queenPos[row]=0;
	}
	public void queenMoveOn(int row){
		queenPos[row]++;
	}
	public int getScale(){
		return scale;
	}
	public int peek(){
		return row;
	}
	public void update(int _row){
		row = _row;
	}
}

class Queen{
	private int row;
	private Board board;
	public Queen(int label, Board b){
		row = label;
		board = b;
	}
	private boolean posCheck(int row){
		for(int preRow=1;preRow<row;preRow++){
			if(board.queenPosOfRow(preRow)==board.queenPosOfRow(row)||Math.abs(board.queenPosOfRow(preRow)-board.queenPosOfRow(row))-Math.abs(preRow-row)==0)
				return false;
		}
		return true;
	}
	public void process(){
		board.queenMoveOn(row);
		if(board.queenPosOfRow(row)>board.getScale()){
			board.queenReset(row); 
			board.update(row-1);
			return ;
		}
		if(posCheck(row)){
			board.update(row+1);
		}
		else{
			board.update(row);
		}
	}
}

class Publisher{
	private Board board;
	private Queen[] queens;
	public Publisher(Board b){
		board = b;
		queens = new Queen[board.getScale()+1];
		for(int i=1;i<=board.getScale();i++){
			queens[i] = new Queen(i, board);
		}
	}
	public void run(){
		int curRow = board.peek();
		while(curRow>0){
			if(curRow==board.getScale()+1){
				board.foundAns();
				board.update(board.getScale());
				curRow = board.peek();
				continue;
			}
			queens[curRow].process();
			curRow = board.peek();
		}
	}
}
public class Blackboard{
	public static void main(String args[]){
		Board b= new Board(4);
		Publisher p= new Publisher(b);
		p.run();
		System.out.println(b.getAns());
	}
}
