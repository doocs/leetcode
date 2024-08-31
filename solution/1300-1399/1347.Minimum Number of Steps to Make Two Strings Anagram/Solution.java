class Solution {
    public int minSteps(String s, String t) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        int ans = 0;
        for (char c : t.toCharArray()) {
            if (--cnt[c - 'a'] < 0) {
                ans++;
            }
        }
        return ans;
    }
}
