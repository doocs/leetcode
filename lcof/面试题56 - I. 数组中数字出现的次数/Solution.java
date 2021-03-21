class Solution {
    public int[] singleNumbers(int[] nums) {
        int eor = 0;
        for (int num : nums) {
            eor ^= num;
        }
        // # 找出最右边的 1
        int diff = eor & (~eor + 1);
        int a = 0;
        for (int num : nums) {
            if ((num & diff) == 0) {
                a ^= num;
            }
        }
        int b = eor ^ a;
        return new int[]{a, b};
    }
}