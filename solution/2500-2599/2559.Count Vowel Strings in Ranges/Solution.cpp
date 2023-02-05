class Solution {
public:
    vector<int> vowelStrings(vector<string>& words, vector<vector<int>>& queries) {
        vector<int> t;
        unordered_set<char> vowels = {'a', 'e', 'i', 'o', 'u'};
        for (int i = 0; i < words.size(); ++i) {
            if (vowels.count(words[i][0]) && vowels.count(words[i].back())) {
                t.push_back(i);
            }
        }
        vector<int> ans;
        for (auto& q : queries) {
            int x = lower_bound(t.begin(), t.end(), q[1] + 1) - lower_bound(t.begin(), t.end(), q[0]);
            ans.push_back(x);
        }
        return ans;
    }
};