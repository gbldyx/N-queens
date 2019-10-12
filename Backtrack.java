public class Backtrack{
	private int[] queenPos;
	private int scale;
	private int ansCount;
	public Backtrack(int n){
		scale = n;
		queenPos = new int[n+1];
		for(int i = 0; i <= n; i++){
			queenPos[i] = 0;
		}
		ansCount = 0;
	}
	public int getAns(){
		return ansCount;
	}
	private boolean posCheck(int row){
		for(int preRow=1;preRow<row;preRow++){
			if(queenPos[preRow]==queenPos[row]||Math.abs(queenPos[preRow]-queenPos[row])-Math.abs(preRow-row)==0)
				return false;
		}
		return true;
	}
	public void backtrack(){
		int row = 1;
		while(row>0){
			queenPos[row]++;
			if(queenPos[row]<=scale){
				if(posCheck(row)==false)
					continue;
				row++;
				if(row<=scale)
					continue;
				ansCount++;
				queenPos[scale]++;
				row=scale;
			}
			else{
				queenPos[row]=0;
				row--;
			}
		}
	}
	public static void main(String args[]){
		Backtrack b = new Backtrack(8);
		b.backtrack();
		System.out.println(b.getAns());
	}
}
