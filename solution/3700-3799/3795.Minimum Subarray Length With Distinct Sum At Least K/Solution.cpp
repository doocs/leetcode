class Solution {
public:
    int minLength(vector<int>& nums, int k) {
        int n = nums.size();
        int ans = n + 1;
        unordered_map<int, int> cnt;
        int l = 0;
        long long s = 0;
        for (int r = 0; r < n; ++r) {
            int x = nums[r];
            if (++cnt[x] == 1) {
                s += x;
            }
            while (s >= k) {
                ans = min(ans, r - l + 1);
                int y = nums[l];
                if (--cnt[y] == 0) {
                    s -= y;
                }
                ++l;
            }
        }
        return ans > n ? -1 : ans;
    }
};
