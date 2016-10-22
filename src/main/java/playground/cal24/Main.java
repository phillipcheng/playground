package playground.cal24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static void main(String args[]){
		System.out.println("24 pt, input 4 digits seperated by comma, for example: 1,2,3,4");		 
		int[] input;
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String strInput;
		try {
			while(!"q".equals(strInput = bf.readLine())){
				System.out.println(strInput);
				if ("all".equals(strInput)){
					int count = 0;
					for(int i=1; i<=10; i++) {
						for (int j=i; j<=10; j++) {
							for (int k=j; k<=10; k++) {
								for (int l=k; l<=10; l++) {
									count++;
									input=new int[4];
									input[0]=i;
									input[1]=j;
									input[2]=k;
									input[3]=l;
									Seed24 s = new Seed24(input);
									s.run();
									if (s.result!=null){
										System.out.println(s);
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
						Seed24 s = new Seed24(input);
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