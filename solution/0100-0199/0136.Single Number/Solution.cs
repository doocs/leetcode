public class Solution {
    public int SingleNumber(int[] nums) {
        return nums.Aggregate(0, (a, b) => a ^ b);
    }
}
