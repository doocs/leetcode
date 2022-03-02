class Solution {
public:
    vector<int> counter;

    vector<string> findWords(vector<vector<char>>& board, vector<string>& words) {
        counter.resize(26);
        for (auto& b : board)
            for (auto& c : b)
                ++counter[c - 'a'];
        unordered_set<string> s(words.begin(), words.end());
        vector<string> ans;
        for (string word : s)
            if (find(word, board))
                ans.push_back(word);
        return ans;
    }

    bool find(string& word, vector<vector<char>>& board) {
        if (!check(word)) return false;
        for (int i = 0; i < board.size(); ++i)
            for (int j = 0; j < board[0].size(); ++j)
                if (dfs(i, j, 0, word, board))
                    return true;
        return false;
    }

    bool dfs(int i, int j, int l, string& word, vector<vector<char>>& board) {
        if (l == word.size()) return true;
        if (i < 0 || i >= board.size() || j < 0 || j >= board[0].size() || board[i][j] != word[l]) return false;
        char c = board[i][j];
        board[i][j] = '0';
        bool ans = false;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k)
        {
            int x = i + dirs[k], y = j + dirs[k + 1];
            ans = ans || dfs(x, y, l + 1, word, board);
        }
        board[i][j] = c;
        return ans;
    }

    bool check(string word) {
        vector<int> cnt(26);
        for (char c : word)
            ++cnt[c - 'a'];
        for (int i = 0; i < 26; ++i)
            if (counter[i] < cnt[i])
                return false;
        return true;
    }
};