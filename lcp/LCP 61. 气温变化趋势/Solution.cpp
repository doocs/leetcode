class Solution {
public:
    int temperatureTrend(vector<int>& temperatureA, vector<int>& temperatureB) {
        int ans = 0, f = 0;
        for (int i = 0; i < temperatureA.size() - 1; ++i) {
            int x = temperatureA[i + 1] - temperatureA[i];
            int y = temperatureB[i + 1] - temperatureB[i];
            if ((x == 0 && y == 0) || x * y > 0) {
                ans = max(ans, ++f);
            } else {
                f = 0;
            }
        }
        return ans;
    }
};