class Solution {
public:
    vector<int> findClosedNumbers(int num) {
        vector<int> ans(2, -1);
        int dirs[3] = {0, 1, 0};
        for (int p = 0; p < 2; ++p) {
            int a = dirs[p], b = dirs[p + 1];
            int x = num;
            for (int i = 1; i < 31; ++i) {
                if ((x >> i & 1) == a && (x >> (i - 1) & 1) == b) {
                    x ^= 1 << i;
                    x ^= 1 << (i - 1);
                    int j = 0, k = i - 2;
                    while (j < k) {
                        while (j < k && (x >> j & 1) == b) {
                            ++j;
                        }
                        while (j < k && (x >> k & 1) == a) {
                            --k;
                        }
                        if (j < k) {
                            x ^= 1 << j;
                            x ^= 1 << k;
                        }
                    }
                    ans[p] = x;
                    break;
                }
            }
        }
        return ans;
    }
};