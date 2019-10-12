public class Callback{
	private int[] queenPos;
	private int scale;
	private int ansCount;
	public Callback(int n){
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
	public void callback(int row){
		if(row==scale+1){
			ansCount++;
			return ;
		}
		for(int pos=1;pos<=scale;pos++){
			queenPos[row]=pos;
			if(posCheck(row)==false)
				continue;
			callback(row+1);
		}
	}
	public static void main(String args[]){
		Callback c = new Callback(4);
		c.callback(1);
		System.out.println(c.getAns());
	}
}
