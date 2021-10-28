class Solution {
    public int largestAltitude(int[] gain) {
        int res = 0;
        int t = 0;
        for (int h : gain) {
            t += h;
            res = Math.max(res, t);
        }
        return res;
    }
}