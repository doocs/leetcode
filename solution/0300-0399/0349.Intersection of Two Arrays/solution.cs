public class Solution {
    public int[] Intersection(int[] nums1, int[] nums2) {
        List<int> result = new List<int>();
        HashSet<int> arr1 = new(nums1);
        HashSet<int> arr2 = new(nums2);
        foreach (int x in arr1) {
            if (arr2.Contains(x)) {
                result.Add(x);
            }
        }
        return result.ToArray();
    }
}
