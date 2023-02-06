class Solution {
public:
    int minimumSwap(string s1, string s2) {
        int xy = 0, yx = 0;
        for (int i = 0; i < s1.size(); ++i) {
            char a = s1[i], b = s2[i];
            xy += a < b;
            yx += a > b;
        }
        if ((xy + yx) % 2) {
            return -1;
        }
        return xy / 2 + yx / 2 + xy % 2 + yx % 2;
    }
};