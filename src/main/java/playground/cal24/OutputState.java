package playground.cal24;

import java.util.*;

public class OutputState {
	public boolean visited = false;
	
	public static int totalOpNum = 6;
	
	public static int opPlus = 0;
	public static int opMinus = 1;
	public static int opTimes = 2;
	public static int opDiv = 3;
	public static int opMMinus = 4;
	public static int opMDiv = 5;
	
	//the target, i.e 24
	public static int target = 24;
	//this step's input
	public float[] input;
	//steps taken till now
	public List<OneStep> steps = new ArrayList<OneStep>();
	
	public OutputState nextSibling;
	public OutputState firstChild;
	public OutputState parent;
	
	public OutputState(){
		this.steps = new ArrayList<OneStep>();
	}
	
	public OutputState(int[] a){
		input = new float[a.length];
		for (int i=0; i<a.length; i++){
			input[i] = a[i];
		}
		this.steps = new ArrayList<OneStep>();
	}
	
	public OutputState(float[] a){
		input = new float[a.length];
		for (int i=0; i<a.length; i++){
			input[i] = a[i];
		}
		this.steps = new ArrayList<OneStep>();
	}
	
	public OutputState(float[] input, List<OneStep> history){
		this.input = input;
		this.steps = new ArrayList<OneStep>();
		if (history != null){
			this.steps.addAll(history);
		}
	}
	
	public void genNextSibling(){
		if (nextSibling == null){
			OneStep lastStep = null;
			OneStep nextStep = null; 
			if (this.steps.size() < 1){
				lastStep = null;
			}else{
				lastStep = this.steps.get(this.steps.size()-1);
			}
			if (lastStep == null){
				nextStep = null;
			}else{
				nextStep = lastStep.nextStep();
			}
			if (nextStep == null){
				//no more sibling
				this.nextSibling = null;
			}else{
				OutputState os = new OutputState();
				os.parent = this.parent;
				int numSteps = this.steps.size();
				for (int i=0; i<numSteps-1; i++){
					os.steps.add(steps.get(i));
				}
				os.steps.add(nextStep);
				os.input  = nextStep.takeStep();
				this.nextSibling = os;
			}
		}
	}
	
	public void genFirstChild(){
		if ( firstChild == null){
			if (this.input.length <= 1){
				//no more child
				this.firstChild = null;
			}else{
				OutputState os = new OutputState();
				os.parent = this;
				int numSteps = this.steps.size();
				for (int i=0; i<numSteps; i++){
					os.steps.add(steps.get(i));
				}
				OneStep step = new OneStep(this.input);
				os.steps.add(step);
				os.input = step.takeStep();
				this.firstChild = os;
			}
		}
	}
	
	/*
	 * return the next node according to depth-first-search algorithm
	 */
	public OutputState dfsNext() {		
		
		if (!visited){
			genNextSibling();
			genFirstChild();
			visited = true;
		}
		
		OutputState os = null;
		if ( this.firstChild == null){
			//no more child
			if (this.nextSibling == null){
				// no more sibling
				if (this.parent == null){
					//return null
				}else{
					
					this.parent.firstChild = null;
					os = this.parent.dfsNext();
					//need to clean me
					this.parent = null;
				}
			}else{
				os = this.nextSibling;
				//need to clean me
				this.parent.firstChild = this.nextSibling;
				this.nextSibling = null;
				this.parent = null;
				this.firstChild = null;
			}
		}else{
			//do not need to cleanup
			os = this.firstChild;
		}
		return os;
	}
	
	@Override
	public int hashCode() {
		int res = 0;
		for (int i=0; i<input.length; i++){
			res += input[i];
		}
		return res;
	}
	
	@Override
	public boolean equals(Object obj){
		OutputState os = (OutputState) obj;
		for (int i=0; i<input.length; i++) {
			if (os.input[i]!= this.input[i]){
				return false;
			}
		}
		return true;
	}
	
	
	String genExp(String a, String b, int op) {
		if (op == 0) {
			return "(" + a + "+" + b +")";
		}else if (op == 1) {
			return "(" + a + "-" + b +")";
		}else if (op == 2) {
			return "(" + a + "*" + b +")";
		}else if (op == 3) {
			return "(" + a + "/" + b +")";
		}else if (op == 4) {
			return "(" + b + "-" + a +")";
		}else if (op == 5) {
			return "(" + b + "/" + a +")";
		}else{
			return "NO SUPPORTED OP:" + op;
		}
	}
	
	public String genExpFromSteps(int[] orgInput){
		String[] strRes = new String[orgInput.length];
		for (int i=0; i<orgInput.length; i++){
			strRes[i] = orgInput[i] + "";
		}
		
		for (int i=0; i< steps.size(); i++){
			String[] strNext = new String[strRes.length-1];
			OneStep os = steps.get(i);
			strNext[0] = genExp (strRes[os.idxi], strRes[os.idxj], os.op);
			//before i: 0 .. i-1; move one step right: +1
			for (int k = 0; k<=os.idxi-1; k++){
				strNext[k+1] = strRes[k];
			}
			//between i and j: i+1...j-1; keep
			for (int k = os.idxi+1; k<=os.idxj-1; k++){
				strNext[k] = strRes[k];
			}
			//after j: j+1 .. next.length - 1; move one step left: -1
			for (int k = os.idxj+1; k<=strNext.length; k++){
				strNext[k-1] = strRes[k];
			}
			strRes = strNext;
		}
		return strRes[0];
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean checkTarget() {
		if (input.length == 1 && check(input[0], target)){
			return true;
		}else{
			return false;
		}
	}
	
	boolean check(float result, float target){
		if ((result > target - 0.00001) && (result < target + 0.00001)){
			return true;
		}else
			return false;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("this state's input:");
		for (int i=0; i<input.length; i++){
			sb.append(input[i] + ",");
		}
		sb.append("\n");
		for (int i=0; i<steps.size(); i++){
			OneStep os = steps.get(i);
			sb.append(os.toString());
		}
		return sb.toString();
	}
	
}