class Solution {
    public boolean checkIfExist(int[] arr) {
        Map<Integer, Integer> m = new HashMap<>();
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            m.put(arr[i], i);
        }
        for (int i = 0; i < n; ++i) {
            if (m.containsKey(arr[i] << 1) && m.get(arr[i] << 1) != i) {
                return true;
            }
        }
        return false;
    }
}