class Solution {
    public int minimumDeletions(String s) {
        int lb = 0, ra = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == 'a') {
                ++ra;
            }
        }
        int ans = n;
        for (int i = 0; i < n; ++i) {
            ra -= (s.charAt(i) == 'a' ? 1 : 0);
            ans = Math.min(ans, lb + ra);
            lb += (s.charAt(i) == 'b' ? 1 : 0);
        }
        return ans;
    }
}