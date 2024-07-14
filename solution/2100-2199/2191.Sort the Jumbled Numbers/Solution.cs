public class Solution {
    public int[] SortJumbled(int[] mapping, int[] nums) {
        Func<int, int> f = (int x) => {
            if (x == 0) {
                return mapping[0];
            }
            int y = 0;
            int k = 1;
            int num = x;
            while (num != 0) {
                int v = mapping[num % 10];
                y = k * v + y;
                k *= 10;
                num /= 10;
            }
            return y;
        };

        int n = nums.Length;
        List<(int, int)> arr = new List<(int, int)>();
        for (int i = 0; i < n; ++i) {
            arr.Add((f(nums[i]), i));
        }
        arr.Sort();

        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = nums[arr[i].Item2];
        }
        return ans;
    }
}