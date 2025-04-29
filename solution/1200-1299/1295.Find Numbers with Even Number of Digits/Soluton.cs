public class Solution {
    public int FindNumbers(int[] nums) {
        return nums.Count(x => x.ToString().Length % 2 == 0);
    }
}