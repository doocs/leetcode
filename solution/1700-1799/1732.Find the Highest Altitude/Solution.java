class Solution {
    public int largestAltitude(int[] gain) {
        int ans = 0, s = 0;
        for (int v : gain) {
            s += v;
            ans = Math.max(ans, s);
        }
        return ans;
    }
}