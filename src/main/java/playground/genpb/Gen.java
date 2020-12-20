package playground.genpb;


import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;


public class Gen {

    static Logger logger = LogManager.getLogger(Gen.class);

    static String usage(){
        return String.format("usage: result-limit, #-of-tests");
    }
    public static final void main(String[] args){
        if (args.length<2){
            usage();
            return;
        }

        String slimit = args[0];
        int limit = 100;
        try {
            limit = Integer.parseInt(slimit);
        }catch(Exception e){
            logger.info(String.format("can't parse %s as integer", slimit), e);
        }

        String snumber = args[1];
        int number = 100;
        try {
            number = Integer.parseInt(snumber);
        }catch(Exception e){
            logger.info(String.format("can't parse %s as integer", snumber), e);
        }
        Random r = new Random();

        Set<Exp> expSet = new HashSet<Exp>();
        while(expSet.size()<number){
            int op = r.nextInt(2);//0 for +, 1 for minus
            if (op == 0) {
                int bound = r.nextInt(limit);
                int a = r.nextInt(bound+1);
                int b = r.nextInt(limit - a+1);
                Exp exp = new Exp(a, b, "+");
                expSet.add(exp);
            }else if (op == 1){
                int a = r.nextInt(limit);
                int b = 0;
                if (a>0)
                    b = r.nextInt(a);
                Exp exp = new Exp(a, b, "-");
                expSet.add(exp);
            }
        }
        Iterator<Exp> it = expSet.iterator();
        int column=5;
        int blank = 15;

        while(it.hasNext()){
            String line = "";
            for (int i=0; i<column; i++){
                if (it.hasNext()) {
                    Exp exp = it.next();
                    line += exp;
                    line += StringUtils.repeat(" ", blank-exp.length());
                }
            }
            logger.info(line);
        }
    }
}
