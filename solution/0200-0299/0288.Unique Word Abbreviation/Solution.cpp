class ValidWordAbbr {
public:
    unordered_map<string, unordered_set<string>> words;

    ValidWordAbbr(vector<string>& dictionary) {
        for (auto word : dictionary) {
            auto abbr = wordAbbr(word);
            words[abbr].insert(word);
        }
    }

    bool isUnique(string word) {
        auto abbr = wordAbbr(word);
        if (!words.count(abbr)) return true;
        auto vals = words[abbr];
        return vals.size() == 1 && vals.count(word);
    }

    string wordAbbr(string s) {
        int n = s.size();
        return n < 3 ? s : s.substr(0, 1) + to_string(n - 2) + s.substr(n - 1, 1);
    }
};

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr* obj = new ValidWordAbbr(dictionary);
 * bool param_1 = obj->isUnique(word);
 */