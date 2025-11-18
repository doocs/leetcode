public class Solution {
    public int MinOperations(int[] nums, int k) {
        return nums.Sum() % k;
    }
}
