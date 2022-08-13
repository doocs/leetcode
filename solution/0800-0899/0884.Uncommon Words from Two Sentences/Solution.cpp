class Solution {
public:
    vector<string> uncommonFromSentences(string s1, string s2) {
        unordered_map<string, int> counter;

        auto add = [&](const string& s) {
            stringstream ss(s);
            string word;
            while (ss >> word) ++counter[move(word)];
        };

        add(s1);
        add(s2);
        vector<string> ans;
        for (auto& [word, n] : counter)
            if (n == 1)
                ans.push_back(word);
        return ans;
    }
};