class Solution {
public:
    int minDays(int n) {
        static const auto f = [] {
            constexpr int mx = 100001;
            vector<int> f(mx, INT_MAX);

            f[0] = -1;

            for (int i = 1; i < mx; i++) {
                for (int j = 1; j * (j + 1) / 2 <= i; j++) {
                    int s = j * (j + 1) / 2;
                    f[i] = std::min(f[i], f[i - s] + j + 1);
                }
            }

            return f;
        }();

        return f[n];
    }
};