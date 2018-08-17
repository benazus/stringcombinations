import java.util.ArrayList;
import java.util.List;

public class Combination {
    private List<Integer> combination;
    private int size;

    // Accepts a string of index values indicating position of keys, in a combination of size n
    public Combination(int size, ArrayList<Integer> array) {
        this.size = size;
        combination = new ArrayList<Integer>(array);
    }

    public String toString() {
        return "size: " + size + "; combination: " + combination.toString() + "\n";
    }

    public int getSize() {
        if(size > 0)
            return this.size;
        else
            return 0;
    }

    public List<Integer> getCombination() {
        return this.combination;
    }
}