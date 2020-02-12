import java.util.*;

class Solution {
    public boolean checkIfExist(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i] * 2) && i != map.get(arr[i] * 2))
                return true;
        }
        return false;
    }
}