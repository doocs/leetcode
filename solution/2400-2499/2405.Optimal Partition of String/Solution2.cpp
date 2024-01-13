class Solution {
public:
    int partitionString(string s) {
        int ans = 1;
        int v = 0;
        for (char c : s) {
            int i = c - 'a';
            if ((v >> i) & 1) {
                v = 0;
                ++ans;
            }
            v |= 1 << i;
        }
        return ans;
    }
};