public class Solution {
    public int BeautifulSubsets(int[] nums, int k) {
        int ans = -1;
        int[] cnt = new int[1010];
        int n = nums.Length;

        void Dfs(int i) {
            if (i >= n) {
                ans++;
                return;
            }
            Dfs(i + 1);
            bool ok1 = nums[i] + k >= 1010 || cnt[nums[i] + k] == 0;
            bool ok2 = nums[i] - k < 0 || cnt[nums[i] - k] == 0;
            if (ok1 && ok2) {
                cnt[nums[i]]++;
                Dfs(i + 1);
                cnt[nums[i]]--;
            }
        }

        Dfs(0);
        return ans;
    }
}
