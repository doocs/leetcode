class Solution {
public:
    int maxValue(int n, int index, int maxSum) {
        auto sum = [](long x, int cnt) {
            return x >= cnt ? (x + x - cnt + 1) * cnt / 2 : (x + 1) * x / 2 + cnt - x;
        };
        int left = 1, right = maxSum;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (sum(mid - 1, index) + sum(mid, n - index) <= maxSum) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
};