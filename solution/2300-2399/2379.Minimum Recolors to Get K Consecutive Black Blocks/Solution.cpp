class Solution {
public:
    int minimumRecolors(string blocks, int k) {
        int cnt = 0, n = blocks.size();
        int i = 0;
        for (; i < k; ++i) cnt += blocks[i] == 'W';
        int ans = cnt;
        for (; i < n; ++i) {
            cnt += blocks[i] == 'W';
            cnt -= blocks[i - k] == 'W';
            ans = min(ans, cnt);
        }
        return ans;
    }
};