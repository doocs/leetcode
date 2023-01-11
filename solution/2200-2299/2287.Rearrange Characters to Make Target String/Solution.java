class Solution {
    public int rearrangeCharacters(String s, String target) {
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt1[s.charAt(i) - 'a'];
        }
        for (int i = 0; i < target.length(); ++i) {
            ++cnt2[target.charAt(i) - 'a'];
        }
        int ans = 100;
        for (int i = 0; i < 26; ++i) {
            if (cnt2[i] > 0) {
                ans = Math.min(ans, cnt1[i] / cnt2[i]);
            }
        }
        return ans;
    }
}