class Solution {
public:
    int countCompleteDayPairs(vector<int>& hours) {
        int cnt[24]{};
        int ans = 0;
        for (int x : hours) {
            ans += cnt[(24 - x % 24) % 24];
            ++cnt[x % 24];
        }
        return ans;
    }
};