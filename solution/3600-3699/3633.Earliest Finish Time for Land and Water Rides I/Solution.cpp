class Solution {
public:
    int earliestFinishTime(vector<int>& landStartTime, vector<int>& landDuration, vector<int>& waterStartTime, vector<int>& waterDuration) {
        int x = calc(landStartTime, landDuration, waterStartTime, waterDuration);
        int y = calc(waterStartTime, waterDuration, landStartTime, landDuration);
        return min(x, y);
    }

    int calc(vector<int>& a1, vector<int>& t1, vector<int>& a2, vector<int>& t2) {
        int minEnd = INT_MAX;
        for (int i = 0; i < a1.size(); ++i) {
            minEnd = min(minEnd, a1[i] + t1[i]);
        }
        int ans = INT_MAX;
        for (int i = 0; i < a2.size(); ++i) {
            ans = min(ans, max(minEnd, a2[i]) + t2[i]);
        }
        return ans;
    }
};
