class Solution {
public:
    int minimumCardPickup(vector<int>& cards) {
        unordered_map<int, int> m;
        int ans = 1e6;
        for (int i = 0; i < cards.size(); ++i) {
            int c = cards[i];
            if (m.count(c)) ans = min(ans, i - m[c] + 1);
            m[c] = i;
        }
        return ans == 1e6 ? -1 : ans;
    }
};