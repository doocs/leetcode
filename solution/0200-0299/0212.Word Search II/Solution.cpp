class Trie {
public:
    vector<Trie*> children;
    string w;
    Trie()
        : children(26)
        , w("") { }

    void insert(string& w) {
        Trie* node = this;
        for (char c : w) {
            c -= 'a';
            if (!node->children[c]) node->children[c] = new Trie();
            node = node->children[c];
        }
        node->w = w;
    }
};

class Solution {
public:
    vector<int> dirs = {-1, 0, 1, 0, -1};

    vector<string> findWords(vector<vector<char>>& board, vector<string>& words) {
        Trie* trie = new Trie();
        unordered_set<string> res;
        for (auto& w : words) trie->insert(w);
        for (int i = 0; i < board.size(); ++i)
            for (int j = 0; j < board[0].size(); ++j)
                dfs(trie, i, j, board, res);
        vector<string> ans;
        for (auto& w : res) ans.emplace_back(w);
        return ans;
    }

    void dfs(Trie* node, int i, int j, vector<vector<char>>& board, unordered_set<string>& res) {
        int idx = board[i][j] - 'a';
        if (!node->children[idx]) return;
        node = node->children[idx];
        if (node->w != "") res.insert(node->w);
        char c = board[i][j];
        board[i][j] = '0';

        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < board.size() && y >= 0 && y < board[0].size() && board[x][y] != '0') dfs(node, x, y, board, res);
        }
        board[i][j] = c;
    }
};