class Solution {
public:
    int findLucky(vector<int>& arr) {
        int cnt[510];
        memset(cnt, 0, sizeof(cnt));
        for (int x : arr) {
            ++cnt[x];
        }
        int ans = -1;
        for (int x = 1; x < 510; ++x) {
            if (cnt[x] == x) {
                ans = x;
            }
        }
        return ans;
    }
};