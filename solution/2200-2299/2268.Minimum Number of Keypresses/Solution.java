class Solution {
    public int minimumKeypresses(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        Arrays.sort(cnt);
        int ans = 0;
        for (int i = 1, j = 1; i <= 26; ++i) {
            ans += j * cnt[26 - i];
            if (i % 9 == 0) {
                ++j;
            }
        }
        return ans;
    }
}