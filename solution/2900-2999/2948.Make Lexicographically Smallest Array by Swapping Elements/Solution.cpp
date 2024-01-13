class Solution {
public:
    vector<int> lexicographicallySmallestArray(vector<int>& nums, int limit) {
        int n = nums.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) {
            return nums[i] < nums[j];
        });
        vector<int> ans(n);
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && nums[idx[j]] - nums[idx[j - 1]] <= limit) {
                ++j;
            }
            vector<int> t(idx.begin() + i, idx.begin() + j);
            sort(t.begin(), t.end());
            for (int k = i; k < j; ++k) {
                ans[t[k - i]] = nums[idx[k]];
            }
            i = j;
        }
        return ans;
    }
};