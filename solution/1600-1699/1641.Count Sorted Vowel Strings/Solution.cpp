class Solution {
public:
    int countVowelStrings(int n) {
        int f[5] = {1, 1, 1, 1, 1};
        for (int i = 0; i < n - 1; ++i) {
            int s = 0;
            for (int j = 0; j < 5; ++j) {
                s += f[j];
                f[j] = s;
            }
        }
        return accumulate(f, f + 5, 0);
    }
};