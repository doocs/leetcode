class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        int ans = 1, prev = Integer.MIN_VALUE;
        int f0 = 1, f1 = 1;
        for (int x : nums) {
            if (x == prev) {
                f1 = f0 + 1;
            } else if (x == prev + 1) {
                ++f0;
                ++f1;
            } else if (x == prev + 2) {
                f0 = f1 + 1;
                f1 = 1;
            } else {
                f0 = 1;
                f1 = 1;
            }
            ans = Math.max(ans, Math.max(f0, f1));
            prev = x;
        }
        return ans;
    }
}
