class Solution {
public:
    int maxFrequency(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        long long s[n + 1];
        s[0] = 0;
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        int left = 1, right = n;
        auto check = [&](int cnt) {
            for (int i = 0; i < n + 1 - cnt; ++i) {
                int j = i + cnt - 1;
                if (1LL * nums[j] * cnt - (s[j + 1] - s[i]) <= k) {
                    return true;
                }
            }
            return false;
        };
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (check(mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
};