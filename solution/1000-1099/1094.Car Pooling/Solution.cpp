class Solution {
public:
    bool carPooling(vector<vector<int>>& trips, int capacity) {
        vector<int> delta(1001);
        for (auto& trip : trips) {
            int num = trip[0], start = trip[1], end = trip[2];
            delta[start] += num;
            delta[end] -= num;
        }
        int cur = 0;
        for (auto& num : delta) {
            cur += num;
            if (cur > capacity) {
                return false;
            }
        }
        return true;
    }
};