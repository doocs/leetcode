class Solution {
public:
    int minDays(vector<int>& bloomDay, int m, int k) {
        int mx = ranges::max(bloomDay);
        int l = 1, r = mx + 1;
        auto check = [&](int days) {
            int cnt = 0, cur = 0;
            for (int x : bloomDay) {
                cur = x <= days ? cur + 1 : 0;
                if (cur == k) {
                    cnt++;
                    cur = 0;
                }
            }
            return cnt >= m;
        };
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l > mx ? -1 : l;
    }
};
