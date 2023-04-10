public class Solution {
    public int RemoveDuplicates(int[] nums) {
        int k = 0;
        foreach (int x in nums) {
            if (k == 0 || x != nums[k - 1]) {
                nums[k++] = x;
            }
        }
        return k;
    }
}