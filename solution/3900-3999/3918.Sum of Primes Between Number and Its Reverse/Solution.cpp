const int MX = 1000;
bool isPrime[MX + 1];

auto init = [] {
    for (int i = 0; i <= MX; ++i) isPrime[i] = true;
    isPrime[0] = isPrime[1] = false;
    for (int i = 2; i * i <= MX; ++i) {
        if (isPrime[i]) {
            for (int j = i * i; j <= MX; j += i) {
                isPrime[j] = false;
            }
        }
    }
    return 0;
}();

class Solution {
public:
    int sumOfPrimesInRange(int n) {
        int r = 0;
        int tmp = n;
        while (tmp) {
            r = r * 10 + tmp % 10;
            tmp /= 10;
        }
        int low = min(n, r);
        int high = max(n, r);
        int ans = 0;
        for (int x = low; x <= high; ++x) {
            if (isPrime[x]) ans += x;
        }
        return ans;
    }
};
