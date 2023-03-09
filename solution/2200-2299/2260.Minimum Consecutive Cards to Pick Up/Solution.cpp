class Solution {
public:
    int minimumCardPickup(vector<int>& cards) {
        unordered_map<int, int> last;
        int n = cards.size();
        int ans = n + 1;
        for (int i = 0; i < n; ++i) {
            if (last.count(cards[i])) {
                ans = min(ans, i - last[cards[i]] + 1);
            }
            last[cards[i]] = i;
        }
        return ans > n ? -1 : ans;
    }
};