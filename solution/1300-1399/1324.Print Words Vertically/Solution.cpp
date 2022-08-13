class Solution {
public:
    vector<string> printVertically(string s) {
        stringstream in(s);
        vector<string> words;
        string word;
        int n = 0;
        while (in >> word) {
            words.push_back(word);
            n = max(n, (int)word.size());
        }
        int m = words.size();
        vector<string> ans;
        for (int j = 0; j < n; ++j) {
            string t = "";
            for (int i = 0; i < m; ++i) {
                word = words[i];
                t += j < word.size() ? word[j] : ' ';
            }
            while (t.back() == ' ') {
                t.pop_back();
            }
            ans.push_back(t);
        }
        return ans;
    }
};