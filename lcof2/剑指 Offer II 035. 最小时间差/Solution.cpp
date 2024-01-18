class Solution {
public:
    int findMinDifference(vector<string>& timePoints) {
        if (timePoints.size() > 24 * 60) {
            return 0;
        }
        vector<int> mins;
        for (auto& t : timePoints) {
            mins.push_back(stoi(t.substr(0, 2)) * 60 + stoi(t.substr(3)));
        }
        sort(mins.begin(), mins.end());
        mins.push_back(mins[0] + 24 * 60);
        int ans = 1 << 30;
        for (int i = 1; i < mins.size(); ++i) {
            ans = min(ans, mins[i] - mins[i - 1]);
        }
        return ans;
    }
};