class Solution {
public:
    int findMinDifference(vector<string>& timePoints) {
        if (timePoints.size() > 24 * 60)
            return 0;
        vector<int> mins;
        for (auto t : timePoints)
            mins.push_back(stoi(t.substr(0, 2)) * 60 + stoi(t.substr(3)));
        sort(mins.begin(), mins.end());
        mins.push_back(mins[0] + 24 * 60);
        int res = 24 * 60;
        for (int i = 1; i < mins.size(); ++i)
            res = min(res, mins[i] - mins[i - 1]);
        return res;
    }
};