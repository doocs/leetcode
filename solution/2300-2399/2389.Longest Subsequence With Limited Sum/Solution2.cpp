class Solution {
public:
    vector<int> answerQueries(vector<int>& nums, vector<int>& queries) {
        sort(nums.begin(), nums.end());
        int m = queries.size();
        vector<int> idx(m);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) {
            return queries[i] < queries[j];
        });
        vector<int> ans(m);
        int s = 0, j = 0;
        for (int i : idx) {
            while (j < nums.size() && s + nums[j] <= queries[i]) {
                s += nums[j++];
            }
            ans[i] = j;
        }
        return ans;
    }
};