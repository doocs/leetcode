class Solution {
public:
    int minChanges(vector<int>& nums, int k) {
        int d[k + 2];
        memset(d, 0, sizeof(d));
        int n = nums.size();
        for (int i = 0; i < n / 2; ++i) {
            int x = min(nums[i], nums[n - i - 1]);
            int y = max(nums[i], nums[n - i - 1]);
            d[0] += 1;
            d[y - x] -= 1;
            d[y - x + 1] += 1;
            d[max(y, k - x) + 1] -= 1;
            d[max(y, k - x) + 1] += 2;
        }
        int ans = n, s = 0;
        for (int x : d) {
            s += x;
            ans = min(ans, s);
        }
        return ans;
    }
};