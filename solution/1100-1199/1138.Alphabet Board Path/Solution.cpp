class Solution {
public:
    string alphabetBoardPath(string target) {
        string ans;
        int i = 0, j = 0;
        for (char& c : target) {
            int v = c - 'a';
            int x = v / 5, y = v % 5;
            while (j > y) {
                --j;
                ans += 'L';
            }
            while (i > x) {
                --i;
                ans += 'U';
            }
            while (j < y) {
                ++j;
                ans += 'R';
            }
            while (i < x) {
                ++i;
                ans += 'D';
            }
            ans += '!';
        }
        return ans;
    }
};