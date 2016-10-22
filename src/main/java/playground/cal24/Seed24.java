package playground.cal24;

import java.util.*;

public class Seed24{
	//USER INPUT
	int[] input;
	//24 normally
	int target;
	//
	List<OutputState> result;
	
	public Seed24(int[] input) {
		this.input = input;
	}
	
	public void run(){
		int i=0;
		OutputState os = new OutputState(input);
		while( os!= null && i<30){
			os = os.dfsNext();
			if (os != null) {
				//System.out.println(i +":");
				//System.out.println(os);
				if (os.checkTarget()){
					i++;
					if (result == null){
						result = new ArrayList<OutputState>();
					}
					//result.add(os);
					System.out.println(os.genExpFromSteps(input));
				}
			}
		}
		//System.out.println(this);
	}

	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("input:");
		if (result != null){
			for (int i=0; i<input.length; i++){
				sb.append(input[i] + ",");
			}
			sb.append("\n");
			for (int i=0; i<result.size();i++){
				OutputState os = result.get(i);
				sb.append(os.genExpFromSteps(input));
				sb.append("\n");
			}
		}
		return sb.toString();
	}
}