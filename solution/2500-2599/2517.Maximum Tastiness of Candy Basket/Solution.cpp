class Solution {
public:
    int maximumTastiness(vector<int>& price, int k) {
        sort(price.begin(), price.end());
        int left = 0, right = 1e9;
        auto check = [&](int x) {
            int s = price[0];
            int cnt = 1;
            for (int i = 1; i < price.size(); ++i) {
                if (price[i] - s >= x) {
                    s = price[i];
                    ++cnt;
                }
            }
            return cnt >= k;
        };
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (check(mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
};