public class Solution {
    public int[][] MinAbsDiff(int[][] grid, int k) {
        int m = grid.Length, n = grid[0].Length;
        int[][] ans = new int[m - k + 1][];
        for (int i = 0; i <= m - k; ++i) {
            ans[i] = new int[n - k + 1];
            for (int j = 0; j <= n - k; ++j) {
                List<int> nums = new List<int>(k * k);
                for (int x = i; x < i + k; ++x) {
                    for (int y = j; y < j + k; ++y) {
                        nums.Add(grid[x][y]);
                    }
                }

                nums.Sort();

                int d = int.MaxValue;
                for (int t = 1; t < nums.Count; ++t) {
                    if (nums[t] != nums[t - 1]) {
                        d = Math.Min(d, Math.Abs(nums[t] - nums[t - 1]));
                    }
                }

                ans[i][j] = d == int.MaxValue ? 0 : d;
            }
        }
        return ans;
    }
}
