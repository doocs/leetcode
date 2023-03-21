class Solution {
    public int minimumOperations(int[] nums) {
        boolean[] s = new boolean[101];
        s[0] = true;
        int ans = 0;
        for (int x : nums) {
            if (!s[x]) {
                ++ans;
                s[x] = true;
            }
        }
        return ans;
    }
}