class Solution {
    public int partitionString(String s) {
        int ans = 1, mask = 0;
        for (int i = 0; i < s.length(); ++i) {
            int x = s.charAt(i) - 'a';
            if ((mask >> x & 1) == 1) {
                ++ans;
                mask = 0;
            }
            mask |= 1 << x;
        }
        return ans;
    }
}
