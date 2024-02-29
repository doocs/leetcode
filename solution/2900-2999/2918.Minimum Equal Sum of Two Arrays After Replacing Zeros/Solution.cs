public class Solution {
    public long MinSum(int[] nums1, int[] nums2) {
        long s1 = 0, s2 = 0;
        bool hasZero = false;
        foreach (int x in nums1) {
            hasZero |= x == 0;
            s1 += Math.Max(x, 1);
        }
        foreach (int x in nums2) {
            s2 += Math.Max(x, 1);
        }
        if (s1 > s2) {
            return MinSum(nums2, nums1);
        }
        if (s1 == s2) {
            return s1;
        }
        return hasZero ? s2 : -1;
    }
}