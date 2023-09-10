class Solution {
public:
    int countSymmetricIntegers(int low, int high) {
        int ans = 0;
        auto f = [](int x) {
            string s = to_string(x);
            int n = s.size();
            if (n & 1) {
                return 0;
            }
            int a = 0, b = 0;
            for (int i = 0; i < n / 2; ++i) {
                a += s[i] - '0';
                b += s[n / 2 + i] - '0';
            }
            return a == b ? 1 : 0;
        };
        for (int x = low; x <= high; ++x) {
            ans += f(x);
        }
        return ans;
    }
};