public class Solution {
    public int[] Intersect(int[] nums1, int[] nums2) {
        HashSet<int> hs1 = new HashSet<int>(nums1.Concat(nums2).ToArray());
        Dictionary<int, int> dict = new Dictionary<int, int>();
        List<int> result = new List<int>();

        foreach (int x in hs1) {
            dict[x] = 0;
        }

        foreach (int x in nums1) {
            if (dict.ContainsKey(x)) {
                dict[x] += 1;
            } else {
                dict[x] = 1;
            }
        }

        foreach (int x in nums2) {
            if (dict[x] > 0) {
                result.Add(x);
                dict[x] -=1;
            }
        }

        return result.ToArray();
    }
}
