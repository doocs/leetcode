public class Solution {
    public int MinSwaps(int[] nums) {
        int n = nums.Length;
        int[][] arr = new int[n][];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[] { F(nums[i]), nums[i] };
        }
        Array.Sort(arr, (a, b) => {
            if (a[0] != b[0]) {
                return a[0].CompareTo(b[0]);
            }
            return a[1].CompareTo(b[1]);
        });
        Dictionary<int, int> d = new Dictionary<int, int>();
        for (int i = 0; i < n; i++) {
            d[arr[i][1]] = i;
        }
        bool[] vis = new bool[n];
        int ans = n;
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                ans--;
                int j = i;
                while (!vis[j]) {
                    vis[j] = true;
                    j = d[nums[j]];
                }
            }
        }
        return ans;
    }

    private int F(int x) {
        int s = 0;
        while (x != 0) {
            s += x % 10;
            x /= 10;
        }
        return s;
    }
}
