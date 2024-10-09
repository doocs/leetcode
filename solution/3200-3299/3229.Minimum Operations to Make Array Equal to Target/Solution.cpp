class Solution {
public:
    long long minimumOperations(vector<int>& nums, vector<int>& target) {
        using ll = long long;
        ll f = abs(target[0] - nums[0]);
        for (int i = 1; i < nums.size(); ++i) {
            long x = target[i] - nums[i];
            long y = target[i - 1] - nums[i - 1];
            if (x * y > 0) {
                ll d = abs(x) - abs(y);
                if (d > 0) {
                    f += d;
                }
            } else {
                f += abs(x);
            }
        }
        return f;
    }
};