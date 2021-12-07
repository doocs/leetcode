class Solution {
    public int getLastMoment(int n, int[] left, int[] right) {
        int ans = 0;
        for (int t : left) {
            ans = Math.max(ans, t);
        }
        for (int t : right) {
            ans = Math.max(ans, n - t);
        }
        return ans;
    }
}