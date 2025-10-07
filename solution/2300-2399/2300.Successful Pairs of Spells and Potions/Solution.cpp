class Solution {
public:
    vector<int> successfulPairs(vector<int>& spells, vector<int>& potions, long long success) {
        ranges::sort(potions);
        const int m = potions.size();
        vector<int> ans;
        ans.reserve(spells.size());

        for (int v : spells) {
            auto it = ranges::lower_bound(potions, static_cast<double>(success) / v);
            ans.push_back(m - static_cast<int>(it - potions.begin()));
        }
        return ans;
    }
};
