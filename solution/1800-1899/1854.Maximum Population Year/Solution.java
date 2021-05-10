class Solution {
    public int maximumPopulation(int[][] logs) {
        int offset = 1950;
        int[] delta = new int[101];
        for (int[] log : logs) {
            ++delta[log[0] - offset];
            --delta[log[1] - offset];
        }
        int mx = 0, cur = 0, res = 0;
        for (int i = 0; i < 101; ++i) {
            cur += delta[i];
            if (mx < cur) {
                mx = cur;
                res = i;
            }
        }
        return res + offset;
    }
}