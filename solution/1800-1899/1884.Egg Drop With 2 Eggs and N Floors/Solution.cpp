class Solution {
public:
    int twoEggDrop(int n) {
        int f[n + 1];
        memset(f, 0x3f, sizeof(f));
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                f[i] = min(f[i], 1 + max(j - 1, f[i - j]));
            }
        }
        return f[n];
    }
};
