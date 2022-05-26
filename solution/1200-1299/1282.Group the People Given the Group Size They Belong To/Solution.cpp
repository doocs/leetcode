class Solution {
public:
    vector<vector<int>> groupThePeople(vector<int>& groupSizes) {
        unordered_map<int, vector<int>> mp;
        for (int i = 0; i < groupSizes.size(); ++i) mp[groupSizes[i]].push_back(i);
        vector<vector<int>> res;
        for (auto& entry : mp)
        {
            int x = entry.first;
            auto indexes = entry.second;
            for (int i = 0; i < indexes.size(); i += x)
            {
                vector<int> t(indexes.begin() + i, indexes.begin() + i + x);
                res.push_back(t);
            }
        }
        return res;
    }
};