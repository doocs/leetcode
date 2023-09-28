class Solution {
public:
    bool isReflected(vector<vector<int>>& points) {
        const int inf = 1 << 30;
        int minX = inf, maxX = -inf;
        set<pair<int, int>> pointSet;
        for (auto& p : points) {
            minX = min(minX, p[0]);
            maxX = max(maxX, p[0]);
            pointSet.insert({p[0], p[1]});
        }
        int s = minX + maxX;
        for (auto& p : points) {
            if (!pointSet.count({s - p[0], p[1]})) {
                return false;
            }
        }
        return true;
    }
};