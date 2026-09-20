class Solution {
public:
    vector<vector<string>> findLadders(
        string beginWord,
        string endWord,
        vector<string>& wordList
    ) {
        unordered_set<string> words(wordList.begin(), wordList.end());

        vector<vector<string>> ans;

        if (!words.count(endWord))
            return ans;

        // parent[word] = words that can come immediately before it
        unordered_map<string, vector<string>> parent;

        unordered_map<string, int> dist;
        queue<string> q;

        q.push(beginWord);
        dist[beginWord] = 0;

        int wordLen = beginWord.size();
        int targetDist = -1;

        while (!q.empty()) {
            string word = q.front();
            q.pop();

            int d = dist[word];

            if (targetDist != -1 && d >= targetDist)
                continue;

            string next = word;

            for (int i = 0; i < wordLen; i++) {
                char original = next[i];

                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == original)
                        continue;

                    next[i] = c;

                    if (!words.count(next))
                        continue;

                    // First time visiting this word
                    if (!dist.count(next)) {
                        dist[next] = d + 1;
                        q.push(next);

                        parent[next].push_back(word);

                        if (next == endWord)
                            targetDist = d + 1;
                    }

                    // Another shortest path to the same word
                    else if (dist[next] == d + 1) {
                        parent[next].push_back(word);
                    }
                }

                next[i] = original;
            }
        }

        if (!dist.count(endWord))
            return ans;

        // DFS to reconstruct paths
        vector<string> path;
        path.push_back(endWord);

        function<void(string)> dfs = [&](string word) {
            if (word == beginWord) {
                vector<string> current = path;
                reverse(current.begin(), current.end());
                ans.push_back(current);
                return;
            }

            for (string p : parent[word]) {
                path.push_back(p);
                dfs(p);
                path.pop_back();
            }
        };

        dfs(endWord);

        return ans;
    }
};
