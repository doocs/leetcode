class Solution {
    public int[] printNumbers(int n) {
        n = (int) Math.pow(10, n) - 1;
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            res[i] = i + 1;
        }
        return res;
    }
}