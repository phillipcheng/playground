package playground.guess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args){
		System.out.println("AB Guess");		
		int[] input;
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String strInput;
		try {
			while(!"q".equals(strInput = bf.readLine())){
				System.out.println(strInput);
				if ("all".equals(strInput)){
					int count = 0;
					for(int i=0; i<=9; i++) {
						for (int j=i; j<=9; j++) {
							for (int k=j; k<=9; k++) {
								for (int l=k; l<=9; l++) {
									if (i!=j && i!=k && i!=l && j!=k && j!=l && k!=l){
										count++;
										input=new int[4];
										input[0]=i;
										input[1]=j;
										input[2]=k;
										input[3]=l;
										ABGuess s = new ABGuess(input);
										s.run();
									}
								}
							}
						}
					}
				}else{
				
				StringTokenizer st = new StringTokenizer(strInput,",");
				input = new int[st.countTokens()];
				int i = 0;
				boolean ok = false;
				while (st.hasMoreTokens()){
					try {
						input[i] = Integer.parseInt(st.nextToken());
						ok = true;
					}catch(NumberFormatException nfe){
						ok = false;
						break;
					}
					i++;
				}
				if (ok){
					ABGuess s = new ABGuess(input);
					s.run();
				}
			}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
