class Solution {
public:
    int minSwaps(string s) {
        int x = 0;
        for (char& c : s) {
            if (c == '[') {
                ++x;
            } else if (x) {
                --x;
            }
        }
        return (x + 1) / 2;
    }
};