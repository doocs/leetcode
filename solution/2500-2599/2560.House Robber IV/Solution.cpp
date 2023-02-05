class Solution {
public:
    int minCapability(vector<int>& nums, int k) {
        auto f = [&](int x) {
            int cnt = 0, j = -2;
            for (int i = 0; i < nums.size(); ++i) {
                if (nums[i] > x || i == j + 1) {
                    continue;
                }
                ++cnt;
                j = i;
            }
            return cnt >= k;
        };
        int left = 0, right = *max_element(nums.begin(), nums.end());
        while (left < right) {
            int mid = (left + right) >> 1;
            if (f(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
};