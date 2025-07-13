class Solution {
    public int totalReplacements(int[] ranks) {
        int ans = 0;
        int cur = ranks[0];
        for (int x : ranks) {
            if (x < cur) {
                cur = x;
                ++ans;
            }
        }
        return ans;
    }
}