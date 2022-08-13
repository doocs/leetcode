class Solution {
public:
    int minBitFlips(int start, int goal) {
        int t = start ^ goal;
        int ans = 0;
        while (t) {
            ans += t & 1;
            t >>= 1;
        }
        return ans;
    }
};