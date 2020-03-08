class Solution {
    public int[] printNumbers(int n) {
        int nums = (int) Math.pow(10, n);
        int[] res = new int[nums - 1];
        for (int i = 0; i < nums - 1; ++i) {
            res[i] = i + 1;
        }
        return res;
    }
}