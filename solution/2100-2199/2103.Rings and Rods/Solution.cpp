class Solution {
public:
    int countPoints(string rings) {
        unordered_map<int, unordered_set<char>> mp;
        for (int i = 1; i < rings.size(); i += 2) {
            int c = rings[i] - '0';
            mp[c].insert(rings[i - 1]);
        }
        int ans = 0;
        for (int i = 0; i < 10; ++i)
            if (mp[i].size() == 3)
                ++ans;
        return ans;
    }
};