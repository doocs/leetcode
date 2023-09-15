class Solution {
public:
    int minimumFinishTime(vector<vector<int>>& tires, int changeTime, int numLaps) {
        int cost[18];
        memset(cost, 0x3f, sizeof(cost));
        for (auto& e : tires) {
            int f = e[0], r = e[1];
            int s = 0;
            long long t = f;
            for (int i = 1; t <= changeTime + f; ++i) {
                s += t;
                cost[i] = min(cost[i], s);
                t *= r;
            }
        }
        int f[numLaps + 1];
        memset(f, 0x3f, sizeof(f));
        f[0] = -changeTime;
        for (int i = 1; i <= numLaps; ++i) {
            for (int j = 1; j < min(18, i + 1); ++j) {
                f[i] = min(f[i], f[i - j] + cost[j]);
            }
            f[i] += changeTime;
        }
        return f[numLaps];
    }
};