package playground.search;

import java.util.*;

public class MaskedIntArray{
	public int[] input;
	public boolean[] mask;
	public ArrayList<Integer> res;
	
	public MaskedIntArray(int[] input){
		
		this.input = input;
		
		mask = new boolean[input.length];
		for (int i=0; i<input.length; i++){
			mask[i] = false;
		}
		
		res = new ArrayList<Integer>();
	}
	
	public void copyResult() {
		for (int i=0; i<input.length; i++) {
			res.add(input[i]);
		}
	}
	
	public int hashCode() {
		int res = 0;
		for (int i=0; i<input.length; i++){
			res += input[i];
		}
		return res;
	}
	
	public boolean equals(Object m1){
		boolean ret = true;
		MaskedIntArray m = (MaskedIntArray)m1;
		if (res.size() != m.res.size()) {
			ret = false;
		}else{
			for (int i=0; i<res.size(); i++) {
				if (res.get(i).intValue() != m.res.get(i).intValue()){
					ret = false;
				}
			}
		}
		return ret;
	}
	
	public MaskedIntArray clone() {
		MaskedIntArray c = new MaskedIntArray(this.input);
		for (int i=0; i<c.mask.length; i++){
			c.mask[i] = this.mask[i];
		}
		c.res.addAll(this.res);
		return c;
	}
	
	public boolean done() {
		return (this.input.length == this.res.size());
	}
	
	List<MaskedIntArray> perm(){
		List<MaskedIntArray> list = new ArrayList<MaskedIntArray>();
		
		for (int i=0; i<input.length; i++) {
			if (!mask[i]){//not permed yet
				MaskedIntArray mia = this.clone();
				mia.res.add(input[i]);
				mia.mask[i] = true;
				list.add(mia);
			}
		}
		
		return list;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("result:" + res + ".");
		sb.append(" input:");
		for (int i=0; i<input.length; i++) {
			sb.append(input[i]);
			sb.append(",");
			sb.append(mask[i]);
			sb.append(";");
		}
		return sb.toString();
	}
	
	List<MaskedIntArray> genPerm(MaskedIntArray mia) {
		List<MaskedIntArray> list = mia.perm();
		for (int i=0; i<list.size(); i++) {
			MaskedIntArray m = list.get(i);
			if (!m.done()){
				list.addAll(genPerm(m));
			}
		}
		return list;
	}
}