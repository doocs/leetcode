class Solution {
public:
    int minimumChairs(string s) {
        int cnt = 0, left = 0;
        for (char& c : s) {
            if (c == 'E') {
                if (left > 0) {
                    --left;
                } else {
                    ++cnt;
                }
            } else {
                ++left;
            }
        }
        return cnt;
    }
};