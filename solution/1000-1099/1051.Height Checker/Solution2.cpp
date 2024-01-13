class Solution {
public:
    int heightChecker(vector<int>& heights) {
        vector<int> cnt(101);
        for (int& h : heights) ++cnt[h];
        int ans = 0;
        for (int i = 0, j = 0; i < 101; ++i) {
            while (cnt[i]) {
                --cnt[i];
                if (heights[j++] != i) ++ans;
            }
        }
        return ans;
    }
};