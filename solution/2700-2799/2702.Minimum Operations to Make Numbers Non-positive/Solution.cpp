class Solution {
public:
    int minOperations(vector<int>& nums, int x, int y) {
        int l = 0, r = *max_element(nums.begin(), nums.end());
        auto check = [&](int t) {
            long long cnt = 0;
            for (int v : nums) {
                if (v > 1LL * t * y) {
                    cnt += (v - 1LL * t * y + x - y - 1) / (x - y);
                }
            }
            return cnt <= t;
        };
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
};