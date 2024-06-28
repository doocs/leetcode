public class Solution {
    public int[] Intersection(int[] nums1, int[] nums2) {
        HashSet<int> s1 = new HashSet<int>(nums1);
        HashSet<int> s2 = new HashSet<int>(nums2);
        s1.IntersectWith(s2);
        int[] ans = new int[s1.Count];
        s1.CopyTo(ans);
        return ans;
    }
}