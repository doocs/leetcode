class Solution {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, int[]> map = new HashMap<>();
        for (int[] piece : pieces) {
            map.put(piece[0], piece);
        }
        for (int i = 0; i < arr.length;) {
            int[] vals = map.get(arr[i]);
            if (vals == null) {
                return false;
            }
            for (int val : vals) {
                if (arr[i] != val) {
                    return false;
                }
                ++i;
            }
        }
        return true;
    }
}