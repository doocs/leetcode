class Solution {
public:
    vector<int> vowelStrings(vector<string>& words, vector<vector<int>>& queries) {
        unordered_set<char> vowels = {'a', 'e', 'i', 'o', 'u'};
        int n = words.size();
        int s[n + 1];
        s[0] = 0;
        for (int i = 0; i < n; ++i) {
            char a = words[i][0], b = words[i].back();
            s[i + 1] = s[i] + (vowels.count(a) && vowels.count(b));
        }
        vector<int> ans;
        for (auto& q : queries) {
            int l = q[0], r = q[1];
            ans.push_back(s[r + 1] - s[l]);
        }
        return ans;
    }
};