class Solution {
public:
    int partitionString(string s) {
        int ans = 1, mask = 0;
        for (char& c : s) {
            int x = c - 'a';
            if (mask >> x & 1) {
                ++ans;
                mask = 0;
            }
            mask |= 1 << x;
        }
        return ans;
    }
};
