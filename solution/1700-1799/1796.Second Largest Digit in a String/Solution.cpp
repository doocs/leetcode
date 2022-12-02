class Solution {
public:
    int secondHighest(string s) {
        int a = -1, b = -1;
        for (char& c : s) {
            if (isdigit(c)) {
                int v = c - '0';
                if (v > a) {
                    b = a, a = v;
                } else if (v > b && v < a) {
                    b = v;
                }
            }
        }
        return b;
    }
};