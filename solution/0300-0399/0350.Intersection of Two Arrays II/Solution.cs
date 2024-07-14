public class Solution {
    public int[] Intersect(int[] nums1, int[] nums2) {
        Dictionary<int, int> cnt = new Dictionary<int, int>();
        foreach (int x in nums1) {
            if (cnt.ContainsKey(x)) {
                cnt[x]++;
            } else {
                cnt[x] = 1;
            }
        }
        List<int> ans = new List<int>();
        foreach (int x in nums2) {
            if (cnt.ContainsKey(x) && cnt[x] > 0) {
                ans.Add(x);
                cnt[x]--;
            }
        }
        return ans.ToArray();
    }
}