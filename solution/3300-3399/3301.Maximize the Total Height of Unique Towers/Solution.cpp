class Solution {
public:
    long long maximumTotalSum(vector<int>& maximumHeight) {
        ranges::sort(maximumHeight, greater<int>());
        long long ans = 0;
        int mx = 1 << 30;
        for (int x : maximumHeight) {
            x = min(x, mx - 1);
            if (x <= 0) {
                return -1;
            }
            ans += x;
            mx = x;
        }
        return ans;
    }
};
