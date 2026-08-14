class Solution {
    public int maximumLengthSubstring(String s) {
        int ans = 0;
        int[] cnt = new int[26];
        for (int l = 0, r = 0; r < s.length(); ++r) {
            int idx = s.charAt(r) - 'a';
            ++cnt[idx];
            while (cnt[idx] > 2) {
                --cnt[s.charAt(l++) - 'a'];
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}