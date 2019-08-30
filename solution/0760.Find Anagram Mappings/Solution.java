import java.util.*;

class Solution {
    public int[] anagramMappings(int[] A, int[] B) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < B.length; i++) {
            map.put(B[i], i);
        }
        int[] res = new int[B.length];
        int j = 0;
        for (int k : A) {
            res[j++] = map.get(k);
        }

        return res;
    }
}