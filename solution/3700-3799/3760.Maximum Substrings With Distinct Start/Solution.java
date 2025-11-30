class Solution {
    public int maxDistinct(String s) {
        int ans = 0;
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            if (++cnt[s.charAt(i) - 'a'] == 1) {
                ++ans;
            }
        }
        return ans;
    }
}
