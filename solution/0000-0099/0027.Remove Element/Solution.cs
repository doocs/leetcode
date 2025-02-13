public class Solution {
    public int RemoveElement(int[] nums, int val) {
        int k = 0;
        foreach (int x in nums) {
            if (x != val) {
                nums[k++] = x;
            }
        }
        return k;
    }
}