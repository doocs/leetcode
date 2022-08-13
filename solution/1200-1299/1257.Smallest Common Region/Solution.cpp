class Solution {
public:
    string findSmallestRegion(vector<vector<string>>& regions, string region1, string region2) {
        unordered_map<string, string> m;
        for (auto& region : regions)
            for (int i = 1; i < region.size(); ++i)
                m[region[i]] = region[0];
        unordered_set<string> s;
        while (m.count(region1)) {
            s.insert(region1);
            region1 = m[region1];
        }
        while (m.count(region2)) {
            if (s.count(region2)) return region2;
            region2 = m[region2];
        }
        return region1;
    }
};