class Solution {
public:
    int minDays(vector<int>& bloomDay, int m, int k) {
        if (m * k > bloomDay.size()) {
            return -1;
        }
        int mi = INT_MIN, mx = INT_MAX;
        for (int& bd : bloomDay) {
            mi = min(mi, bd);
            mx = max(mx, bd);
        }
        int left = mi, right = mx;
        while (left < right) {
            int mid = left + right >> 1;
            if (check(bloomDay, m, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    bool check(vector<int>& bloomDay, int m, int k, int day) {
        int cnt = 0, cur = 0;
        for (int& bd : bloomDay) {
            cur = bd <= day ? cur + 1 : 0;
            if (cur == k) {
                ++cnt;
                cur = 0;
            }
        }
        return cnt >= m;
    }
};