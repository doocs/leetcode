class Solution {
    public int[] findErrorNums(int[] nums) {
        int eor = 0;
        for (int i = 1; i <= nums.length; ++i) {
            eor ^= (i ^ nums[i - 1]);
        }
        int diff = eor & (~eor + 1);
        int a = 0;
        for (int i = 1; i <= nums.length; ++i) {
            if ((nums[i - 1] & diff) == 0) {
                a ^= nums[i - 1];
            }
            if ((i & diff) == 0) {
                a ^= i;
            }
        }
        int b = eor ^ a;
        for (int num : nums) {
            if (a == num) {
                return new int[]{a, b};
            }
        }
        return new int[]{b, a};
    }
}