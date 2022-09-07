class Solution {
public:
    string sortSentence(string s) {
        istringstream is(s);
        string t;
        vector<string> words;
        while (is >> t) words.push_back(t);
        vector<string> res(words.size());
        for (auto& w : words) {
            int i = w[w.size() - 1] - '1';
            res[i] = w.substr(0, w.size() - 1);
        }
        string ans;
        for (auto& w : res) {
            ans += w + " ";
        }
        ans.pop_back();
        return ans;
    }
};