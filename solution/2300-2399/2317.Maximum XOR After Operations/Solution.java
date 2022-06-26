class Solution {
    public int maximumXOR(int[] nums) {
        int ans = 0;
        for (int v : nums) {
            ans |= v;
        }
        return ans;
    }
}