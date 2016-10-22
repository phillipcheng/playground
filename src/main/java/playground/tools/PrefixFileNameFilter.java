package playground.tools;

import java.io.File;
import java.io.FilenameFilter;

public class PrefixFileNameFilter implements FilenameFilter{

	String prefix;
	String suffix;
	
	public PrefixFileNameFilter(String prefix, String suffix){
		this.prefix = prefix;
		this.suffix = suffix;
	}
	
	public boolean accept(File dir, String name) {
		if (prefix != null && !"".equals(prefix) && !name.startsWith(prefix)){
			return false;
		}
		if (suffix != null && !"".equals(suffix) && !name.endsWith(suffix)){
			return false;
		}
		return true;
	}
}