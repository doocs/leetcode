class Solution {
public:
    vector<string> findOcurrences(string text, string first, string second) {
        istringstream is(text);
        vector<string> words;
        string word;
        while (is >> word) words.push_back(word);
        vector<string> ans;
        for (int i = 0; i < words.size() - 2; ++i)
            if (words[i] == first && words[i + 1] == second)
                ans.push_back(words[i + 2]);
        return ans;
    }
};