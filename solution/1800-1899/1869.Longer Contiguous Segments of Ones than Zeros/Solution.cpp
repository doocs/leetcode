class Solution {
public:
    bool checkZeroOnes(string s) {
        auto f = [&](char x) {
            int cnt = 0, mx = 0;
            for (char& c : s) {
                if (c == x) {
                    mx = max(mx, ++cnt);
                } else {
                    cnt = 0;
                }
            }
            return mx;
        };
        return f('1') > f('0');
    }
};