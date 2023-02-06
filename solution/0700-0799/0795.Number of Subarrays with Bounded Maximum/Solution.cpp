class Solution {
public:
    int numSubarrayBoundedMax(vector<int>& nums, int left, int right) {
        auto f = [&](int x) {
            int cnt = 0, t = 0;
            for (int& v : nums) {
                t = v > x ? 0 : t + 1;
                cnt += t;
            }
            return cnt;
        };
        return f(right) - f(left - 1);
    }
};