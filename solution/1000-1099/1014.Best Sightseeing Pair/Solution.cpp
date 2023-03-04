class Solution {
public:
    int maxScoreSightseeingPair(vector<int>& values) {
        int ans = 0, mx = values[0];
        for (int j = 1; j < values.size(); ++j) {
            ans = max(ans, values[j] - j + mx);
            mx = max(mx, values[j] + j);
        }
        return ans;
    }
};