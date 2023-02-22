class Solution {
public:
    bool isThree(int n) {
        int cnt = 0;
        for (int i = 1; i <= n / i; ++i) {
            if (n % i == 0) {
                cnt += n / i == i ? 1 : 2;
            }
        }
        return cnt == 3;
    }
};