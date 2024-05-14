class Solution {
public:
    vector<string> findLadders(string beginWord, string endWord, vector<string>& wordList) {
        this->endWord = move(endWord);
        this->wordList = move(wordList);
        vis.resize(this->wordList.size(), false);
        ans.push_back(beginWord);
        if (dfs(beginWord)) {
            return ans;
        }
        return {};
    }

private:
    vector<string> ans;
    vector<bool> vis;
    string endWord;
    vector<string> wordList;

    bool check(string& s, string& t) {
        if (s.size() != t.size()) {
            return false;
        }
        int cnt = 0;
        for (int i = 0; i < s.size(); ++i) {
            cnt += s[i] != t[i];
        }
        return cnt == 1;
    }

    bool dfs(string& s) {
        if (s == endWord) {
            return true;
        }
        for (int i = 0; i < wordList.size(); ++i) {
            string& t = wordList[i];
            if (!vis[i] && check(s, t)) {
                vis[i] = true;
                ans.push_back(t);
                if (dfs(t)) {
                    return true;
                }
                ans.pop_back();
            }
        }
        return false;
    }
};