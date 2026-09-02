public class Solution {
    public bool UniformArray(int[] nums1) {
        int mn = int.MaxValue;
        foreach (int x in nums1) {
            if (x % 2 == 1) {
                mn = Math.Min(mn, x);
            }
        }
        if (mn == int.MaxValue) {
            return true;
        }
        foreach (int x in nums1) {
            if (x % 2 == 0 && x < mn) {
                return false;
            }
        }
        return true;
    }
}
