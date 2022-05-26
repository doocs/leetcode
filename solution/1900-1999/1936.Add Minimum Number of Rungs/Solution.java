class Solution {
    public int addRungs(int[] rungs, int dist) {
        int res = 0;
        for (int i = 0, prev = 0; i < rungs.length; ++i) {
            res += (rungs[i] - prev - 1) / dist;
            prev = rungs[i];
        }
        return res;
    }
}