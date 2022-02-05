class Solution {
    public int sumOfUnique(int[] nums) {
        int[] counter = new int[101];
        for (int num : nums) {
            ++counter[num];
        }
        int ans = 0;
        for (int i = 0; i < 101; ++i) {
            if (counter[i] == 1) {
                ans += i;
            }
        }
        return ans;
    }
}