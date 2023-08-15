class Solution {
public:
    string findReplaceString(string s, vector<int>& indices, vector<string>& sources, vector<string>& targets) {
        int n = s.size();
        vector<int> d(n, -1);
        for (int k = 0; k < indices.size(); ++k) {
            int i = indices[k];
            if (s.compare(i, sources[k].size(), sources[k]) == 0) {
                d[i] = k;
            }
        }
        string ans;
        for (int i = 0; i < n;) {
            if (~d[i]) {
                ans += targets[d[i]];
                i += sources[d[i]].size();
            } else {
                ans += s[i++];
            }
        }
        return ans;
    }
};