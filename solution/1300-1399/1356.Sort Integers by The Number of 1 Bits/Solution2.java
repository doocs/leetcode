class Solution {
    public int[] sortByBits(int[] arr) {
        int n = arr.length;
        Integer[] t = new Integer[n];
        for (int i = 0; i < n; ++i) {
            t[i] = arr[i];
        }
        Arrays.sort(t, (a, b) -> {
            int x = Integer.bitCount(a), y = Integer.bitCount(b);
            return x == y ? a - b : x - y;
        });
        for (int i = 0; i < n; ++i) {
            arr[i] = t[i];
        }
        return arr;
    }
}