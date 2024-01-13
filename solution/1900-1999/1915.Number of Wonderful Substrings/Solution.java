class Solution {
    public long wonderfulSubstrings(String word) {
        int[] cnt = new int[1 << 10];
        cnt[0] = 1;
        long ans = 0;
        int st = 0;
        for (char c : word.toCharArray()) {
            st ^= 1 << (c - 'a');
            ans += cnt[st];
            for (int i = 0; i < 10; ++i) {
                ans += cnt[st ^ (1 << i)];
            }
            ++cnt[st];
        }
        return ans;
    }
}