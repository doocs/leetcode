class Solution {
public:
    long long maximumSubarraySum(vector<int>& nums, int k) {
        unordered_map<int, long long> p;
        long long r = LONG_LONG_MIN;
        p[nums[0]] = 0;
        long long s = 0;
        const int n = nums.size();
        for (int i = 0;; ++i) {
            s += nums[i];
            auto t = p.find(nums[i] - k);
            if (t != p.end()) {
                r = max(r, s - t->second);
            }
            t = p.find(nums[i] + k);
            if (t != p.end()) {
                r = max(r, s - t->second);
            }
            if (i + 1 == n)
            break;
            t = p.find(nums[i + 1]);
            if (t == p.end() || t->second > s) {
                p[nums[i + 1]] = s;
            }
        }
        return r == LONG_LONG_MIN ? 0 : r;
    }
};
