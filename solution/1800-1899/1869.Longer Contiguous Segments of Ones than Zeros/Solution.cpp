class Solution {
public:
    bool checkZeroOnes(string s) {
        int n0 = 0, n1 = 0;
        int t0 = 0, t1 = 0;
        for (auto c : s) {
            if (c == '0') {
                ++t0;
                t1 = 0;
            } else {
                ++t1;
                t0 = 0;
            }
            n0 = max(n0, t0);
            n1 = max(n1, t1);
        }
        return n1 > n0;
    }
};