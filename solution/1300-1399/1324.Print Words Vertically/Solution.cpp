class Solution {
public:
    vector<string> printVertically(string s) {
        stringstream ss(s);
        vector<string> words;
        string word;
        int n = 0;
        while (ss >> word) {
            words.emplace_back(word);
            n = max(n, (int) word.size());
        }
        vector<string> ans;
        for (int j = 0; j < n; ++j) {
            string t;
            for (auto& w : words) {
                t += j < w.size() ? w[j] : ' ';
            }
            while (t.size() && t.back() == ' ') {
                t.pop_back();
            }
            ans.emplace_back(t);
        }
        return ans;
    }
};