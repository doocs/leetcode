class Solution {
public:
    int numEquivDominoPairs(vector<vector<int>>& dominoes) {
        vector<int> counter(100);
        int ans = 0;
        for (auto& d : dominoes) {
            int v = d[0] > d[1] ? d[0] * 10 + d[1] : d[1] * 10 + d[0];
            ans += counter[v];
            ++counter[v];
        }
        return ans;
    }
};