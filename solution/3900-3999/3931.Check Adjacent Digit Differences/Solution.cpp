class Solution {
public:
    bool isAdjacentDiffAtMostTwo(string s) {
        for (int i = 1; i < s.size(); ++i) {
            if (abs(s[i - 1] - s[i]) > 2) {
                return false;
            }
        }
        return true;
    }
};