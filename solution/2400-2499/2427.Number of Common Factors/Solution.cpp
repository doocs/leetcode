class Solution {
public:
    int commonFactors(int a, int b) {
        int g = gcd(a, b);
        int ans = 0;
        for (int x = 1; x <= g; ++x) {
            ans += g % x == 0;
        }
        return ans;
    }
};