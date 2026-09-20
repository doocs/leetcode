class Solution {
public:
    vector<vector<string>> findLadders(string beginWord, string endWord, vector<string>& wordList) {
        vector<vector<string>> ans;
        unordered_set<string> words(wordList.begin(), wordList.end());
        if (!words.count(endWord)) {
            return ans;
        }
        words.erase(beginWord);
        unordered_map<string, int> dist{{beginWord, 0}};
        unordered_map<string, unordered_set<string>> prev;
        queue<string> q{{beginWord}};
        bool found = false;
        int step = 0;
        while (!q.empty() && !found) {
            ++step;
            for (int i = q.size(); i > 0; --i) {
                string p = q.front();
                q.pop();
                string t = p;
                for (int j = 0; j < t.size(); ++j) {
                    char ch = t[j];
                    for (char k = 'a'; k <= 'z'; ++k) {
                        t[j] = k;
                        if (dist.count(t) && dist[t] == step) {
                            prev[t].insert(p);
                        }
                        if (!words.count(t)) {
                            continue;
                        }
                        prev[t].insert(p);
                        words.erase(t);
                        q.push(t);
                        dist[t] = step;
                        if (t == endWord) {
                            found = true;
                        }
                    }
                    t[j] = ch;
                }
            }
        }
        function<void(vector<string>&, const string&)> dfs = [&](vector<string>& path, const string& cur) {
            if (cur == beginWord) {
                ans.emplace_back(path.rbegin(), path.rend());
                return;
            }
            for (const string& precursor : prev[cur]) {
                path.push_back(precursor);
                dfs(path, precursor);
                path.pop_back();
            }
        };
        if (found) {
            vector<string> path{endWord};
            dfs(path, endWord);
        }
        return ans;
    }
};
