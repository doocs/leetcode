public class Solution {
    public int FindRepeatNumber(int[] nums) {
        int temp;
        for (int i = 0; i < nums.Length; i++) {
            while (i != nums[i]) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        return -1;
    }
}