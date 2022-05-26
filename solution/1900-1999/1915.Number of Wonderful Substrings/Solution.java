class Solution {
    public long wonderfulSubstrings(String word) {
        int[] counter = new int[1 << 10];
        counter[0] = 1;
        int state = 0;
        long ans = 0;
        for (char c : word.toCharArray()) {
            state ^= (1 << (c - 'a'));
            ans += counter[state];
            for (int i = 0; i < 10; ++i) {
                ans += counter[state ^ (1 << i)];
            }
            ++counter[state];
        }
        return ans;
    }
}