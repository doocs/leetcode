class Solution {
public:
    int minOperations(vector<int>& nums, int sum) {
        const int inf = 1e9;
        vector<int> f(sum + 1, inf);
        f[0] = 0;

        for (int x : nums) {
            for (int w = sum; w >= 0; --w) {
                for (int i = 0, y = x; y <= w; i++, y *= 2) {
                    f[w] = min(f[w], f[w - y] + i);
                }

                for (int i = 1, y = x / 2; y > 0; i++, y /= 2) {
                    for (int j = 0, z = y; z <= w; j++, z *= 2) {
                        f[w] = min(f[w], f[w - z] + i + j);
                    }
                }
            }
        }

        return f[sum] < inf ? f[sum] : -1;
    }
};
