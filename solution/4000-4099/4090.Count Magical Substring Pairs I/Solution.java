class Solution {
    public int countMagicalPairs(String s) {
        int n = s.length();
        int ans = 0;
        for (int j = 0; j < n - 1; j++) {
            Map<Character, Integer> cnt1 = new HashMap<>();
            int[] cnt = new int[27];
            for (int i = j; i >= 0; i--) {
                cnt1.merge(s.charAt(i), 1, Integer::sum);
                cnt[cnt1.size()]++;
            }
            Map<Character, Integer> cnt2 = new HashMap<>();
            for (int k = j + 1; k < n; k++) {
                cnt2.merge(s.charAt(k), 1, Integer::sum);
                ans += cnt[cnt2.size()];
            }
        }
        return ans;
    }
}