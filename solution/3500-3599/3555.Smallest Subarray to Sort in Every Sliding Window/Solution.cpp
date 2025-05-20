class Solution {
public:
    vector<int> minSubarraySort(vector<int>& nums, int k) {
        const int inf = 1 << 30;
        int n = nums.size();
        auto f = [&](int i, int j) -> int {
            int mi = inf, mx = -inf;
            int l = -1, r = -1;
            for (int k = i; k <= j; ++k) {
                if (nums[k] < mx) {
                    r = k;
                } else {
                    mx = nums[k];
                }
                int p = j - k + i;
                if (nums[p] > mi) {
                    l = p;
                } else {
                    mi = nums[p];
                }
            }
            return r == -1 ? 0 : r - l + 1;
        };
        vector<int> ans;
        for (int i = 0; i < n - k + 1; ++i) {
            ans.push_back(f(i, i + k - 1));
        }
        return ans;
    }
};