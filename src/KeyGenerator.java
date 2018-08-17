import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;

public class KeyGenerator {
    private Queue<String> keys;
    private ArrayList<Combination> availableCombinations;
    private int maxKeyCount;
    private int curKeyCount;

    public KeyGenerator(int keyCount) {
        this.maxKeyCount = keyCount;
        this.curKeyCount = 0;
        keys = new LinkedList<String>();
        availableCombinations = new ArrayList<Combination>();
    }

    public void addKey(String s) { insertKey(s); }

    public void generateCombinations() { generate(); }

    public String getNextCombination() { return nextCombination(); }

    public String getNextCombination(int m) { return nextCombination(m); }

    public boolean hasNext() { return !availableCombinations.isEmpty(); }
    
    public boolean hasNext(int m) {
        for (int i = 0; i < availableCombinations.size(); i++) {
            if (availableCombinations.get(i).getSize() == m) {
                return true;
            }
        }
        return false;
    }

    private String nextCombination() {
        if(availableCombinations.isEmpty() == false) {
            Combination c = availableCombinations.get(0);
            availableCombinations.remove(0);

            String combination = "";
            Object[] x = keys.toArray();
            List<Integer> indices = c.getCombination();
            for(int i = 0; i < indices.size(); i++) {
                combination += (String) x[indices.get(i)];

                if(indices.size() > 0 && i < indices.size() - 1)
                    combination += " ";
            }

            return combination;
        }
        else
            return "--EMPTY--";
    }

    private String nextCombination(int m) {
        if(availableCombinations.isEmpty() == false) {
            Combination c = null;
            for(int i = 0; i < availableCombinations.size(); i++)
                if(availableCombinations.get(i).getSize() == m) {
                    c = availableCombinations.get(i);
                    availableCombinations.remove(i);
                }

            if(c != null) {
                String combination = "";
                Object[] x = keys.toArray();
                List<Integer> indices = c.getCombination();
                for (int i = 0; i < indices.size(); i++) {
                    combination += (String) x[indices.get(i)];

                    if (indices.size() > 0 && i < indices.size() - 1)
                        combination += " ";
                }

                return combination;
            }
        }
        return "--EMPTY--";
    }

    private void generate() {
        List<Integer> objects = new ArrayList<Integer>();
        int N = curKeyCount;

        for(int i = 0; i < N; i++)
            objects.add(i);

        RotateData rd = new RotateData();

        for(int M = 0; M <= N; M++) {
            ArrayList<Integer> lastMElements = new ArrayList<Integer>();
            ArrayList<Integer> array = new ArrayList<Integer>();

            for(int i = 0; i < M; i++)
                lastMElements.add(objects.get(N - M + i));

            initializeRotation(N, M, array);
            rd.x = 0;
            rd.y = 0;
            rd.z = 0;
            rd.data = array;
            availableCombinations.add(new Combination(M, lastMElements));

            while(rotate(rd) == true) {
                lastMElements.set(rd.z, objects.get(rd.x));
                availableCombinations.add(new Combination(M, lastMElements));
            }
        }
    }

    private void initializeRotation(int N, int M, ArrayList<Integer> array) {
        array.clear();
        array.add(N + 1);

        for(int i = 1; i < N - M + 1; i++)
            array.add(0);

        for(int i = N - M + 1; i < N + 1; i++)
            array.add(i + M - N);

        array.add(-2);

        if(M == 0)
            array.set(1, 1);
    }

    private boolean rotate(RotateData rd) {
        int i,  j, k;

        for(j = 1; rd.data.get(j) <= 0; j++);

        if(rd.data.get(j - 1) == 0) {
            for(i = j - 1; 1 < i; i--)
                rd.data.set(i, -1);

            rd.data.set(j, 0);
            rd.x = 0;
            rd.z = 0;
            rd.data.set(1, 1);
            rd.y = j - 1;
        }
        else {
            if(j > 1)
                rd.data.set(j - 1, 0);

            for(; rd.data.get(j) > 0; j++);

            k = j - 1;
            i = j;

            for(; rd.data.get(i) == 0; i++)
                rd.data.set(i, -1);

            if(rd.data.get(i) == -1) {
                rd.data.set(i, rd.data.get(k));
                rd.z = rd.data.get(k) - 1;
                rd.x = i - 1;
                rd.y = k - 1;
                rd.data.set(k, -1);
            }
            else {
                if(i == rd.data.get(0))
                    return false;
                else {
                    rd.data.set(j, rd.data.get(i));
                    rd.z = rd.data.get(i) - 1;
                    rd.data.set(i, 0);
                    rd.x = j - 1;
                    rd. y = i - 1;
                }
            }
        }
        return true;
    }

    private void insertKey(String s) {
        String oldKey = "";
        if(curKeyCount >= maxKeyCount) {
            oldKey = keys.poll();

            if(oldKey.equals(null) == false) {
                availableCombinations.clear();
                keys.add(s);
            }
        }
        else {
            keys.add(s);
            curKeyCount++;
        }
    }

    public String toString() {
        return "Keys: " + keys.toString() + "\nCombinations: " + availableCombinations.toString() + "\nmaxKeyCount: " + maxKeyCount + " curKeyCount: " + curKeyCount;
    }
}