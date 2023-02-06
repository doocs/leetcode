class Solution {
public:
    bool splitArraySameAverage(vector<int>& nums) {
        int n = nums.size();
        if (n == 1) return false;
        int s = accumulate(nums.begin(), nums.end(), 0);
        for (int& v : nums) v = v * n - s;
        int m = n >> 1;
        unordered_set<int> vis;
        for (int i = 1; i < 1 << m; ++i) {
            int t = 0;
            for (int j = 0; j < m; ++j)
                if (i >> j & 1) t += nums[j];
            if (t == 0) return true;
            vis.insert(t);
        }
        for (int i = 1; i < 1 << (n - m); ++i) {
            int t = 0;
            for (int j = 0; j < (n - m); ++j)
                if (i >> j & 1) t += nums[m + j];
            if (t == 0 || (i != (1 << (n - m)) - 1 && vis.count(-t))) return true;
        }
        return false;
    }
};