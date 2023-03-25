class Solution {
public:
    int minTime(vector<int>& time, int m) {
        int left = 0, right = 0;
        for (int x : time) {
            right += x;
        }
        auto check = [&](int t) -> bool {
            int s = 0, mx = 0;
            int d = 1;
            for (int x : time) {
                s += x;
                mx = max(mx, x);
                if (s - mx > t) {
                    s = x;
                    mx = x;
                    ++d;
                }
            }
            return d <= m;
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