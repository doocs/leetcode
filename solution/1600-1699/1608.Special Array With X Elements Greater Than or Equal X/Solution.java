class Solution {
    public int specialArray(int[] nums) {
        for (int x = 1; x <= nums.length; ++x) {
            int cnt = 0;
            for (int v : nums) {
                if (v >= x) {
                    ++cnt;
                }
            }
            if (cnt == x) {
                return x;
            }
        }
        return -1;
    }
}