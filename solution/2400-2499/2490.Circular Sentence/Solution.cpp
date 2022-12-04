class Solution {
public:
    bool isCircularSentence(string sentence) {
        if (sentence[0] != sentence[sentence.size() - 1]) return false;
        istringstream is(sentence);
        vector<string> ss;
        string s;
        while (is >> s) ss.emplace_back(s);
        for (int i = 1; i < ss.size(); ++i) {
            if (ss[i][0] != ss[i - 1][ss[i - 1].size() - 1]) {
                return false;
            }
        }
        return true;
    }
};