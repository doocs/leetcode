class Solution {
    public int rearrangeCharacters(String s, String target) {
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt1[c - 'a'];
        }
        for (char c : target.toCharArray()) {
            ++cnt2[c - 'a'];
        }
        int ans = 100;
        for (int i = 0; i < 26; ++i) {
            if (cnt2[i] > 0) {
                if (cnt1[i] < cnt2[i]) {
                    return 0;
                }
                ans = Math.min(ans, cnt1[i] / cnt2[i]);
            }
        }
        return ans;
    }
}