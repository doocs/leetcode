class Solution {
public:
    int minOperations(vector<int>& nums, int sum) {
        const int inf = 1e9;
        vector<int> f(sum + 1, inf);
        f[0] = 0;

        for (int x : nums) {
            for (int w = sum; w >= 0; --w) {
                int i = 0, y = x;
                while (y <= w) {
                    f[w] = min(f[w], f[w - y] + i);
                    ++i;
                    y <<= 1;
                }

                i = 1;
                y = x >> 1;
                while (y > 0) {
                    if (y <= w) {
                        f[w] = min(f[w], f[w - y] + i);
                    }
                    ++i;
                    y >>= 1;
                }
            }
        }

        return f[sum] == inf ? -1 : f[sum];
    }
};
