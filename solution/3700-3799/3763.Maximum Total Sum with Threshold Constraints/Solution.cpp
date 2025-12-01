class Solution {
public:
    long long maxSum(vector<int>& nums, vector<int>& threshold) {
        int n = nums.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int a, int b) { return threshold[a] < threshold[b]; });

        multiset<int> ms;
        long long ans = 0;
        int i = 0;

        for (int step = 1;; ++step) {
            while (i < n && threshold[idx[i]] <= step) {
                ms.insert(nums[idx[i]]);
                ++i;
            }
            if (ms.empty()) {
                break;
            }

            auto it = prev(ms.end());
            ans += *it;
            ms.erase(it);
        }
        return ans;
    }
};
