public class Solution {
    public int SmallestDivisor(int[] nums, int threshold) {
        int l = 1;
        int r = nums.Max();
        while (l < r) {
            int mid = (l + r) >> 1;
            int s = 0;
            foreach (int x in nums) {
                s += (x + mid - 1) / mid;
            }
            if (s <= threshold) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
