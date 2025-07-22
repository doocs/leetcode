class Solution {
public:
    int minSwaps(string s) {
        int n0 = ranges::count(s, '0');
        int n1 = s.size() - n0;
        if (abs(n0 - n1) > 1) {
            return -1;
        }
        auto calc = [&](int c) -> int {
            int cnt = 0;
            for (int i = 0; i < s.size(); ++i) {
                int x = s[i] - '0';
                if ((i & 1 ^ c) != x) {
                    ++cnt;
                }
            }
            return cnt / 2;
        };
        if (n0 == n1) {
            return min(calc(0), calc(1));
        }
        return calc(n0 > n1 ? 0 : 1);
    }
};
