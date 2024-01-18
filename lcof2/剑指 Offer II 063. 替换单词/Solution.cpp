class Solution {
public:
    string replaceWords(vector<string>& dictionary, string sentence) {
        unordered_set<string> s(dictionary.begin(), dictionary.end());
        istringstream is(sentence);
        vector<string> words;
        string ss;
        while (is >> ss) words.push_back(ss);
        for (int i = 0; i < words.size(); ++i) {
            string word = words[i];
            for (int j = 1; j <= word.size(); ++j) {
                string t = word.substr(0, j);
                if (s.count(t)) {
                    words[i] = t;
                    break;
                }
            }
        }
        string ans = "";
        for (string& word : words) ans += word + " ";
        ans.pop_back();
        return ans;
    }
};