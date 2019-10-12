class Pipe{
	private int row;
	public Pipe(){
		row = 0;
	}
	public void write(int _row){
		row = _row;
	}
	public int read(){
		return row;
	}
}

public class Filter{
	private Pipe in;
	private Pipe out;
	private int[] queenPos;
	private int ansCount;
	private int scale;
	public Filter(int n, Pipe inPipe, Pipe outPipe){
		scale = n;
		in = inPipe;
		out = outPipe;
		in.write(1);
		queenPos = new int[n+1];
		for(int i=0;i<=n;i++){
			queenPos[i]=0;
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
	public void process(){
		int curRow = 1;
		while(curRow>0){
			curRow = in.read();
			if(curRow==scale+1){
				ansCount++;
				out.write(scale);
			}
			else{
				queenPos[curRow]++;
				if(queenPos[curRow]>scale){
					queenPos[curRow]=0;
					out.write(curRow-1);
					continue;
				}
				if(posCheck(curRow)){
					out.write(curRow+1);
				}
				else{
					out.write(curRow);
				}
			}
		}
	}
	public static void main(String args[]){
		Pipe p = new Pipe();
		Filter f = new Filter(8, p, p);
		f.process();
		System.out.println(f.getAns());
	}
}
			
