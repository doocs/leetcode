class Solution {
public:
    int findLucky(vector<int>& arr) {
        int cnt[501]{};
        for (int x : arr) {
            ++cnt[x];
        }
        for (int x = 500; x; --x) {
            if (x == cnt[x]) {
                return x;
            }
        }
        return -1;
    }
};