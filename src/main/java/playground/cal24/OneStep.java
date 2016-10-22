package playground.cal24;

/* 
 * input[idxi] op input[idxj]
 */
public class OneStep {
	float[] input;
	int idxi;
	int idxj;
	int op;
	
	public OneStep(float[] input){
		this.input = input;
		idxi = 0;
		idxj = 1;
		op = 0;		
	}
	/*
	 * generate the next of this step according to the last input, input is kept intact
	 */
	public OneStep nextStep(){
		OneStep os = new OneStep(this.input);
		
		os.idxi = this.idxi;
		os.idxj = this.idxj;
		os.op = this.op;
		
		if (op == OutputState.totalOpNum-1){
			if (idxj == input.length -1 ){
				if (idxi == idxj-1){
					//no next step
					os = null;
				}else{
					//not last idxi, move idxi, set idxj to idxi + 1
					os.idxi = os.idxi + 1;
					os.idxj = os.idxi + 1;
					os.op = 0;
				}
			}else{
				//not last idxj move idxj
				os.idxj++;
				os.op = 0;
			}
		}else{
			//not last op, move op
			os.op++;
		}
		return os;		
	}
	
	/*
	 * take the step to change the input 
	 */
	public float[] takeStep(){
		float r = calculate(input[idxi] ,input[idxj], op);
		//reconstruct the input
		float[] next = new float[input.length -1];
		next[0] = r;
		//i<j
		//before i: 0 .. i-1; move one step right: +1
		for (int k = 0; k<=idxi-1; k++){
			next[k+1] = input[k];
		}
		//between i and j: i+1...j-1; keep
		for (int k = idxi+1; k<=idxj-1; k++){
			next[k] = input[k];
		}
		//after j: j+1 .. next.length - 1; move one step left: -1
		for (int k = idxj+1; k<=next.length; k++){
			next[k-1] = input[k];
		}
		return next;
	}
	

	static float calculate(float a, float b, int op) {
		if (op == 0) {
			return a+b;
		}else if (op == 1) {
			return a-b;
		}else if (op == 2) {
			return a*b;
		}else if (op == 3) {
			return div(a,b);
		}else if (op == 4) {
			return b-a;
		}else if (op == 5) {
			return div(b,a);
		}else{
			return 0;
		}
	}
	
	static float div(float a, float b) {
		if (b == 0)
			return 0;
		else
			return a/b;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("input:");
		for (int i=0; i<input.length; i++){
			sb.append(input[i]+",");
		}
		sb.append("idxi:" + idxi);
		sb.append(",idxj:" + idxj);
		sb.append(",op:" + op);
		sb.append("\n");
		return sb.toString();
	}
}
