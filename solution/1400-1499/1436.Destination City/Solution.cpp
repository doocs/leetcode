class Solution {
public:
    string destCity(vector<vector<string>>& paths) {
        unordered_map<string, string> mp;
        for (auto& path : paths) mp[path[0]] = path[1];
        string a = paths[0][0];
        while (mp.find(a) != mp.end()) a = mp[a];
        return a;
    }
};