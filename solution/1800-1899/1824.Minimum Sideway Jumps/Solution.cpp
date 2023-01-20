class Solution {
public:
    int minSideJumps(vector<int>& obstacles) {
        const int inf = 1 << 30;
        int f[3] = {1, 0, 1};
        for (int i = 1; i < obstacles.size(); ++i) {
            for (int j = 0; j < 3; ++j) {
                if (obstacles[i] == j + 1) {
                    f[j] = inf;
                    break;
                }
            }
            int x = min({f[0], f[1], f[2]}) + 1;
            for (int j = 0; j < 3; ++j) {
                if (obstacles[i] != j + 1) {
                    f[j] = min(f[j], x);
                }
            }
        }
        return min({f[0], f[1], f[2]});
    }
};