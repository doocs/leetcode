class Solution {
public:
    vector<long long> unmarkedSumArray(vector<int>& nums, vector<vector<int>>& queries) {
        int n = nums.size();
        long long s = accumulate(nums.begin(), nums.end(), 0LL);
        vector<bool> mark(n);
        vector<pair<int, int>> arr;
        for (int i = 0; i < n; ++i) {
            arr.emplace_back(nums[i], i);
        }
        sort(arr.begin(), arr.end());
        vector<long long> ans;
        int m = queries.size();
        for (int i = 0, j = 0; i < m; ++i) {
            int index = queries[i][0], k = queries[i][1];
            if (!mark[index]) {
                mark[index] = true;
                s -= nums[index];
            }
            for (; k && j < n; ++j) {
                if (!mark[arr[j].second]) {
                    mark[arr[j].second] = true;
                    s -= arr[j].first;
                    --k;
                }
            }
            ans.push_back(s);
        }
        return ans;
    }
};