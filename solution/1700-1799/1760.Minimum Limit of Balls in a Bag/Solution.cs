public class Solution {
    public int MinimumSize(int[] nums, int maxOperations) {
        int l = 1, r = nums.Max();
        while (l < r) {
            int mid = (l + r) >> 1;
            long s = 0;
            foreach (int x in nums) {
                s += (x - 1) / mid;
            }
            if (s <= maxOperations) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
