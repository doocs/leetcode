class Solution {
public:
    int maximumTastiness(vector<int>& price, int k) {
        sort(price.begin(), price.end());
        int l = 0, r = price.back() - price[0];
        auto check = [&](int x) -> bool {
            int cnt = 0, pre = -x;
            for (int& cur : price) {
                if (cur - pre >= x) {
                    pre = cur;
                    ++cnt;
                }
            }
            return cnt >= k;
        };
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
};