class Solution {
    public int sumOfUnique(int[] nums) {
        int[] counter = new int[101];
        for (int num : nums) {
            ++counter[num];
        }
        int res = 0;
        for (int i = 1; i < 101; ++i) {
            if (counter[i] == 1) {
                res += i;
            }
        }
        return res;
    }
}