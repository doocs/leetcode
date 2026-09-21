class Solution {
public:
    int numPoints(vector<vector<int>>& darts, int r) {
        int n = darts.size();
        int maxDarts = 1;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                auto centers = possibleCenters(darts[i][0], darts[i][1], darts[j][0], darts[j][1], r);
                for (auto& c : centers) {
                    maxDarts = max(maxDarts, countDarts(c[0], c[1], darts, r));
                }
            }
        }
        return maxDarts;
    }

private:
    vector<array<double, 2>> possibleCenters(int x1, int y1, int x2, int y2, int r) {
        vector<array<double, 2>> centers;
        double dx = x2 - x1, dy = y2 - y1;
        double d = sqrt(dx * dx + dy * dy);
        if (d > 2.0 * r) {
            return centers;
        }
        double midX = (x1 + x2) / 2.0, midY = (y1 + y2) / 2.0;
        double distToCenter = sqrt(1.0 * r * r - (d / 2.0) * (d / 2.0));
        double offsetX = distToCenter * dy / d;
        double offsetY = distToCenter * -dx / d;
        centers.push_back({midX + offsetX, midY + offsetY});
        centers.push_back({midX - offsetX, midY - offsetY});
        return centers;
    }

    int countDarts(double x, double y, vector<vector<int>>& darts, int r) {
        int count = 0;
        for (auto& dart : darts) {
            if (hypot(dart[0] - x, dart[1] - y) <= r + 1e-7) {
                ++count;
            }
        }
        return count;
    }
};
