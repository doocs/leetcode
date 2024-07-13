class Solution {
public:
    int tripletCount(vector<int>& a, vector<int>& b, vector<int>& c) {
        int cnt1[2]{};
        int cnt2[2]{};
        int cnt3[2]{};
        for (int x : a) {
            ++cnt1[__builtin_popcount(x) & 1];
        }
        for (int x : b) {
            ++cnt2[__builtin_popcount(x) & 1];
        }
        for (int x : c) {
            ++cnt3[__builtin_popcount(x) & 1];
        }
        int ans = 0;
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                for (int k = 0; k < 2; ++k) {
                    if ((i + j + k) % 2 == 0) {
                        ans += cnt1[i] * cnt2[j] * cnt3[k];
                    }
                }
            }
        }
        return ans;
    }
};