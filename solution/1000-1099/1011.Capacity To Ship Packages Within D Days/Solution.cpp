class Solution {
public:
    int shipWithinDays(vector<int>& weights, int days) {
        int left = 1, right = 25000000;
        while (left < right) {
            int mid = left + right >> 1;
            if (check(weights, days, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    bool check(vector<int>& weights, int days, int capacity) {
        int cnt = 1, t = 0;
        for (auto w : weights) {
            if (w > capacity) {
                return false;
            }
            if (t + w <= capacity) {
                t += w;
            } else {
                ++cnt;
                t = w;
            }
        }
        return cnt <= days;
    }
};