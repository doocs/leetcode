class Solution {
public:
    int findInteger(int k, int digit1, int digit2) {
        if (digit1 == 0 && digit2 == 0) {
            return -1;
        }
        if (digit1 > digit2) {
            swap(digit1, digit2);
        }
        queue<long long> q{{0}};
        while (1) {
            long long x = q.front();
            q.pop();
            if (x > INT_MAX) {
                return -1;
            }
            if (x > k && x % k == 0) {
                return x;
            }
            q.emplace(x * 10 + digit1);
            if (digit1 != digit2) {
                q.emplace(x * 10 + digit2);
            }
        }
    }
};