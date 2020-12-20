package playground.genpb;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Exp {
    private int a;
    private int b;
    private String op;

    public String toString(){
        return String.format("%d %s %d = ", a, op, b);
    }

    public int length(){
        String sa = a+"";
        String sb = b+"";
        return sa.length()+sb.length();
    }

    public boolean equals(Object that){
        if (that instanceof Exp){
            Exp thatExp = (Exp)that;
            if (thatExp.a == this.a && thatExp.b == this.b ||
                    thatExp.a == this.b && thatExp.b == this.a
            ){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
