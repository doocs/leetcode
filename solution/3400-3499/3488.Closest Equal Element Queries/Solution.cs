public class Solution {
    public IList<int> SolveQueries(int[] nums, int[] queries) {
        int n = nums.Length;
        int m = n * 2;
        int[] d = new int[m];
        Array.Fill(d, m);

        Dictionary<int, int> left = new Dictionary<int, int>();
        for (int i = 0; i < m; i++) {
            int x = nums[i % n];
            if (left.ContainsKey(x)) {
                d[i] = Math.Min(d[i], i - left[x]);
            }
            left[x] = i;
        }

        Dictionary<int, int> right = new Dictionary<int, int>();
        for (int i = m - 1; i >= 0; i--) {
            int x = nums[i % n];
            if (right.ContainsKey(x)) {
                d[i] = Math.Min(d[i], right[x] - i);
            }
            right[x] = i;
        }

        for (int i = 0; i < n; i++) {
            d[i] = Math.Min(d[i], d[i + n]);
        }

        List<int> ans = new List<int>();
        foreach (int query in queries) {
            ans.Add(d[query] >= n ? -1 : d[query]);
        }

        return ans;
    }
}