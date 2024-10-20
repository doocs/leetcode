class Solution {
    public int numberOfSubstrings(String s, int k) {
        int[] cnt = new int[26];
        int ans = 0, l = 0;
        for (int r = 0; r < s.length(); ++r) {
            int c = s.charAt(r) - 'a';
            ++cnt[c];
            while (cnt[c] >= k) {
                --cnt[s.charAt(l) - 'a'];
                l++;
            }
            ans += l;
        }
        return ans;
    }
}
