class Solution {
public:
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        unordered_map<string, vector<string>> chars;
        for (auto s : strs) {
            string k = s;
            sort(k.begin(), k.end());
            chars[k].emplace_back(s);
        }
        vector<vector<string>> res;
        for (auto it = chars.begin(); it != chars.end(); ++it) {
            res.emplace_back(it->second);
        }
        return res;
    }
};