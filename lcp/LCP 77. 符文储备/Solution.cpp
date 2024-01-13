class Solution {
public:
    int runeReserve(vector<int>& runes) {
        sort(runes.begin(), runes.end());
        int ans = 0;
        for (int i = 0, j = 0; j < runes.size(); ++j) {
            if (j && runes[j] - runes[j - 1] > 1) {
                i = j;
            } else {
                ans = max(ans, j - i + 1);
            }
        }
        return ans;
    }
};