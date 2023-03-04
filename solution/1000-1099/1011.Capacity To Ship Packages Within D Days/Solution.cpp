class Solution {
public:
    int shipWithinDays(vector<int>& weights, int days) {
        int left = 0, right = 0;
        for (auto& w : weights) {
            left = max(left, w);
            right += w;
        }
        auto check = [&](int mx) {
            int ws = 0, cnt = 1;
            for (auto& w : weights) {
                ws += w;
                if (ws > mx) {
                    ws = w;
                    ++cnt;
                }
            }
            return cnt <= days;
        };
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
};