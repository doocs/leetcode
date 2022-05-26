class Solution {
public:
    int minSpeedOnTime(vector<int>& dist, double hour) {
        int left = 1, right = 1e7;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(dist, mid, hour)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return check(dist, left, hour) ? left : -1;
    }

    bool check(vector<int>& dist, int speed, double hour) {
        double res = 0;
        for (int i = 0; i < dist.size(); ++i) {
            double cost = dist[i] * 1.0 / speed;
            res += (i == dist.size() - 1 ? cost : ceil(cost));
        }
        return res <= hour;
    }
};