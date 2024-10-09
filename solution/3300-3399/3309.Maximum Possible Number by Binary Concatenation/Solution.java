class Solution {
    private int[] nums;

    public int maxGoodNumber(int[] nums) {
        this.nums = nums;
        int ans = f(0, 1, 2);
        ans = Math.max(ans, f(0, 2, 1));
        ans = Math.max(ans, f(1, 0, 2));
        ans = Math.max(ans, f(1, 2, 0));
        ans = Math.max(ans, f(2, 0, 1));
        ans = Math.max(ans, f(2, 1, 0));
        return ans;
    }

    private int f(int i, int j, int k) {
        String a = Integer.toBinaryString(nums[i]);
        String b = Integer.toBinaryString(nums[j]);
        String c = Integer.toBinaryString(nums[k]);
        return Integer.parseInt(a + b + c, 2);
    }
}
