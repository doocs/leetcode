class Solution {
public:
    int minLengthAfterRemovals(string s) {
        int a = 0;
        for (char c : s) {
            if (c == 'a') {
                ++a;
            }
        }
        int b = s.size() - a;
        return abs(a - b);
    }
};
