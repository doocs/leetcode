class Solution {
public:
    int findLongestChain(vector<vector<int>>& pairs) {
        sort(pairs.begin(), pairs.end(), [](vector<int>& a, vector<int> b) {
            return a[1] < b[1];
        });
        int ans = 0, cur = INT_MIN;
        for (auto& p : pairs) {
            if (cur < p[0]) {
                cur = p[1];
                ++ans;
            }
        }
        return ans;
    }
};