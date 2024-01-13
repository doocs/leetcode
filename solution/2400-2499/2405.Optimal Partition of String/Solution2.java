class Solution {
    public int partitionString(String s) {
        int v = 0;
        int ans = 1;
        for (char c : s.toCharArray()) {
            int i = c - 'a';
            if (((v >> i) & 1) == 1) {
                v = 0;
                ++ans;
            }
            v |= 1 << i;
        }
        return ans;
    }
}