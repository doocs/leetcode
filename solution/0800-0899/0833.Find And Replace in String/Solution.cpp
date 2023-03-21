class Solution {
public:
    string findReplaceString(string s, vector<int>& indices, vector<string>& sources, vector<string>& targets) {
        int n = s.size();
        vector<int> d(n, -1);
        for (int i = 0; i < indices.size(); ++i) {
            int j = indices[i];
            string source = sources[i];
            if (s.substr(j, source.size()) == source) {
                d[j] = i;
            }
        }
        string ans;
        for (int i = 0; i < n;) {
            if (d[i] >= 0) {
                ans += targets[d[i]];
                i += sources[d[i]].size();
            } else {
                ans += s[i++];
            }
        }
        return ans;
    }
};