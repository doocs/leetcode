class Solution {
    public int singleNumber(int[] nums) {
        int[] bits = new int[32];
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 32; ++j) {
                bits[j] += ((nums[i] >> j) & 1);
            }
        }
        
        int res = 0;
        for (int i = 0; i < 32; ++i) {
            if (bits[i] % 3 != 0) {
                res += (1 << i);
            }
        }
        return res;
        
    }
}