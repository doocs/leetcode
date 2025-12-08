class Solution {
    public int maxSameLengthRuns(String s) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                ++j;
            }
            int m = j - i;
            ans = Math.max(ans, cnt.merge(m, 1, Integer::sum));
            i = j;
        }
        return ans;
    }
}
