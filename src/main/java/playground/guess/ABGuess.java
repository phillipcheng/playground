package playground.guess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class OneGuess{
	public int[] thisTry;
	public int[] res;
	
	public OneGuess(int[] thisTry, int[] res){
		this.thisTry = thisTry;
		this.res = res;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("One Guess:");
		for (int i=0; i<thisTry.length;i++){
			sb.append(thisTry[i]+",");
		}
		sb.append("r value:" + res[0]);
		sb.append(",r position:" + res[1]);
		return sb.toString();
	}
}

class OneCandidate{
	int[] guess;
	int distinctNumber;
	
	/**
	 * @param intGuess: integer which will be converted
	 * @param length: number of digits
	 */
	public OneCandidate(int intGuess, int length){
		guess = new int[length];
		int i=length-1;
		Set<Integer> set = new HashSet<Integer>();
		while (i >= 0){
			int digit = intGuess % 10;
			intGuess = intGuess / 10;			
			guess[i] = digit;
			i--;
			set.add(digit);
		}
		this.distinctNumber =  -1* set.size(); //for reverse order
	}
}

public class ABGuess {
	//contains all the not possible digits
	public Set<Integer> notHave = new HashSet<Integer>();
	//contains all the must appear digits
	public Set<Integer> mustHave = new HashSet<Integer>();
	//contains all the mayBe guesses, these number(guess set-and mustHave) < res[0] 
	public Set<OneGuess> mayHave = new HashSet<OneGuess>();
	
	public SortedMap<Integer, List<OneCandidate>> candidateMap = new TreeMap<Integer,List<OneCandidate>>();
	
	int[] answer;
	public List<OneGuess> steps = new ArrayList<OneGuess>();
	public List<OneCandidate> candidates = new ArrayList<OneCandidate>();
	
	public ABGuess(int[] input){
		this.answer = input;
		
		int end = 1;
		int i = 0;
		while (i < input.length){
			end = end * 10;
			i++;
		}
		
		for ( i=0; i< end; i++){
			OneCandidate oc = new OneCandidate(i, input.length);
			List<OneCandidate> l = candidateMap.get(oc.distinctNumber);
			if (l == null){
				l = new ArrayList<OneCandidate>();
				candidateMap.put(oc.distinctNumber, l);
			}
			l.add(oc);
		}
		Iterator<Integer> it = candidateMap.keySet().iterator();
		while (it.hasNext()){
			List<OneCandidate> l = candidateMap.get(it.next());
			candidates.addAll(l);
		}		
		
		/*
		Iterator<OneCandidate> lo = candidates.iterator();
		while (lo.hasNext()){
			OneCandidate oc = lo.next();
			System.out.println(toString(oc.guess));
		}*/
	}
	
	/**
	 * try another guess based on lastGuess and the lastResult get
	 * @param lastGuess
	 * @param lastRes
	 * @return
	 */
	public int[] guess(){
		List<OneCandidate> next = new ArrayList<OneCandidate>();
		//System.out.println("oneGuess:lastGuess:" + toString(lastGuess) + " lastResult:" + toString(lastResult));
		if (steps.size() == 0){
			return candidates.get(0).guess; //first guess 
		}else{
			//filter out the candidates which do not fit for lastGuess' result
			for (int i=0; i<candidates.size();i++){
				OneCandidate oc = candidates.get(i);
				//check the not,must list
				boolean pass = true;
				for (int m=0; m<oc.guess.length; m++){
					if (this.notHave.contains(oc.guess[m])){
						pass = false;
						break;
					}
				}
				if (pass){
					OneGuess og;
					boolean add = true;
					for (int j=0; j<steps.size();j++){
						og = steps.get(j);
						//og = steps.get(steps.size()-1);
						int[] res = judge(og.thisTry, oc.guess);
						//System.out.println("judge: lastGuess:" + toString(og.thisTry) + " thisTry:" + toString(oc.guess) + " res:" + toString(res));
						if (!same(res, og.res)){
							add = false;
							break;
						}
					}
					if (add){
						next.add(oc);
					}
				}else{
					//candidates.remove(oc);
				}
			}
			candidates = next;
			//fetch the first of the remaining as next guess
			if (candidates.size() < 1){
				return null;
			}else{
				return candidates.get(0).guess;
			}
		}
	}
	
	/**
	 * Judge whether my this try validates (according to last guess)
	 * @param lastGuess: my last guess
	 * @param thisTry: my this try
	 * @return result[]: result[0] is the number of right value, 
	 * result[1] is the number of the right (value and position) 
	 */
	public int[] judge(int[] lastGuess, int[] thisTry){
		int[] result = new int[2];
		if (lastGuess.length != thisTry.length){
			return null;
		}
		int ia=0;
		int ib=0;
		for (int i=0; i<thisTry.length; i++){
			for (int j=0; j<lastGuess.length; j++){
				if (thisTry[i] == lastGuess[j]){
					ia++;
					if(i==j){
						ib++;
					}
				}
			}
		}
		result[0] = ia;
		result[1] = ib;
		return result;
	}

	public boolean same(int[] res1, int[] res2){
		if (res1[0] == res2[0] && res1[1] == res2[1] ){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean better(int[] res1, int[] res2){
		if (res1[0] >= res2[0] && res1[1] >= res2[1] ){
			return true;
		}else{
			return false;
		}
	}
	
	public void run(){
		int[] cand;
		int[] res;
		int num = 0;
		OneGuess og;
		while (true){
			cand = guess();
			num++;
			if (cand == null){
				//no more guess
				System.out.println("Failed.");
				break;
			}
			res = judge(answer, cand);
			//update the may,not,must list
			if (res[0] == 0){
				for (int k=0; k< cand.length; k++){
					this.notHave.add(cand[k]);
				}
				//System.out.println("notHave:" + notHave);
			}else if (res[1] ==answer.length){
				//got it
				System.out.println("after " + num + " guess, Succeed. cand:" + toString(cand));
				break;
			}
			og = new OneGuess(cand, res);
			steps.add(og);
			System.out.println(num + ":" + og);
		}
	}
	
	public String toString(int[] array){
		StringBuffer sb = new StringBuffer();
		for (int i=0;i<array.length;i++){
			sb.append(array[i]+",");
		}
		return sb.toString();
	}
}
