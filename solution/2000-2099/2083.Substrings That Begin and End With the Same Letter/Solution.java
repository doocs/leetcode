class Solution {
    public long numberOfSubstrings(String s) {
        int[] cnt = new int[26];
        long ans = 0;
        for (char c : s.toCharArray()) {
            ans += ++cnt[c - 'a'];
        }
        return ans;
    }
}
