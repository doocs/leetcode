class Solution {
public:
    bool winnerOfGame(string colors) {
        int a = 0, b = 0;
        int cnt1 = 0, cnt2 = 0;
        for (char& c : colors) {
            if (c == 'A') {
                ++a;
                if (a > 2) ++cnt1;
                b = 0;
            } else {
                ++b;
                if (b > 2) ++cnt2;
                a = 0;
            }
        }
        return cnt1 > cnt2;
    }
};