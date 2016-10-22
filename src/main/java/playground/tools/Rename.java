package playground.tools;

import java.io.File;
import java.io.FilenameFilter;


public class Rename {
	
	public static void main(String[] args){
		if (args.length < 3){
			System.out.println("Usage: Rename pathname prefix suffix means Remove prefix with suffix");
		}else{
			String pathname = args[0];
			String prefix = args[1];
			String suffix = args[2];
			File f = new File(pathname);
			PrefixFileNameFilter pfnf = new PrefixFileNameFilter(prefix, suffix);
			File[] files = f.listFiles(pfnf);
			System.out.println("matching files:" + files.length);
			for (int i=0; i<files.length; i++){
				File file = files[i];
				String newName = file.getName().substring(prefix.length());
				File destFile = new File(pathname, newName);
				if (file.renameTo(destFile)){
					System.out.println("Successfully renamed:" + file.getName() + " to:" + destFile.getName());
				}
			}
		}
		
	}

}
