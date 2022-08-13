class Solution {
public:
    vector<string> words;
    vector<string> ans;
    unordered_set<string> visited;

    vector<string> findLadders(string beginWord, string endWord, vector<string>& wordList) {
        this->words = wordList;
        ans.resize(0);
        vector<string> t;
        t.push_back(beginWord);
        dfs(beginWord, endWord, t);
        return ans;
    }

    void dfs(string begin, string end, vector<string>& t) {
        if (!ans.empty()) return;
        if (begin == end) {
            ans = t;
            return;
        }
        for (auto word : words) {
            if (visited.count(word) || !check(begin, word)) continue;
            visited.insert(word);
            t.push_back(word);
            dfs(word, end, t);
            t.pop_back();
        }
    }

    bool check(string a, string b) {
        if (a.size() != b.size()) return false;
        int cnt = 0;
        for (int i = 0; i < a.size(); ++i)
            if (a[i] != b[i]) ++cnt;
        return cnt == 1;
    }
};