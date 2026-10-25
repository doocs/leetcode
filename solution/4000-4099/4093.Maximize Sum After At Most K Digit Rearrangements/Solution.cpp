class Solution {
public:
    long long maximizeSum(vector<int>& nums, int k) {
        auto f = [](int x) {
            int cnt[10] = {};
            while (x) {
                int v = x % 10;
                x /= 10;
                cnt[v]++;
            }
            int y = 0;
            for (int i = 9; i >= 0; i--) {
                for (int j = 0; j < cnt[i]; j++) {
                    y = y * 10 + i;
                }
            }
            return y;
        };
        int n = nums.size();
        vector<int> t(n);
        vector<int> ids(n);
        iota(ids.begin(), ids.end(), 0);
        for (int i = 0; i < n; i++) {
            t[i] = f(nums[i]);
        }
        sort(ids.begin(), ids.end(), [&](int i, int j) {
            return t[i] - nums[i] > t[j] - nums[j];
        });
        long long ans = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[ids[i]];
            ans += i < k ? t[ids[i]] : x;
        }
        return ans;
    }
};