class Solution {
    public boolean partitionArray(int[] nums, int k) {
        int n = nums.length;
        if (n % k != 0) {
            return false;
        }
        int m = n / k;
        int mx = Arrays.stream(nums).max().getAsInt();
        int[] cnt = new int[mx + 1];
        for (int x : nums) {
            if (++cnt[x] > m) {
                return false;
            }
        }
        return true;
    }
}
