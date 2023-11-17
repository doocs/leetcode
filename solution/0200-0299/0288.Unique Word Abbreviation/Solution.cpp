class ValidWordAbbr {
public:
    ValidWordAbbr(vector<string>& dictionary) {
        for (auto& s : dictionary) {
            d[abbr(s)].insert(s);
        }
    }

    bool isUnique(string word) {
        string s = abbr(word);
        return !d.count(s) || (d[s].size() == 1 && d[s].count(word));
    }

private:
    unordered_map<string, unordered_set<string>> d;

    string abbr(string& s) {
        int n = s.size();
        return n < 3 ? s : s.substr(0, 1) + to_string(n - 2) + s.substr(n - 1, 1);
    }
};

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr* obj = new ValidWordAbbr(dictionary);
 * bool param_1 = obj->isUnique(word);
 */