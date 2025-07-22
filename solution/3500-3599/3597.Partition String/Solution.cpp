class Solution {
public:
    vector<string> partitionString(string s) {
        unordered_set<string> vis;
        vector<string> ans;
        string t = "";
        for (char c : s) {
            t += c;
            if (!vis.contains(t)) {
                vis.insert(t);
                ans.push_back(t);
                t = "";
            }
        }
        return ans;
    }
};