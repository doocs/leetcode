class Trie {
public:
    vector<Trie*> children;
    int ref;

    Trie()
        : children(26, nullptr)
        , ref(-1) {}

    void insert(const string& w, int ref) {
        Trie* node = this;
        for (char c : w) {
            c -= 'a';
            if (!node->children[c]) {
                node->children[c] = new Trie();
            }
            node = node->children[c];
        }
        node->ref = ref;
    }
};

class Solution {
public:
    vector<string> findWords(vector<vector<char>>& board, vector<string>& words) {
        Trie* tree = new Trie();
        for (int i = 0; i < words.size(); ++i) {
            tree->insert(words[i], i);
        }
        vector<string> ans;
        int m = board.size(), n = board[0].size();

        function<void(Trie*, int, int)> dfs = [&](Trie* node, int i, int j) {
            int idx = board[i][j] - 'a';
            if (!node->children[idx]) {
                return;
            }
            node = node->children[idx];
            if (node->ref != -1) {
                ans.emplace_back(words[node->ref]);
                node->ref = -1;
            }
            int dirs[5] = {-1, 0, 1, 0, -1};
            char c = board[i][j];
            board[i][j] = '#';
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] != '#') {
                    dfs(node, x, y);
                }
            }
            board[i][j] = c;
        };

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                dfs(tree, i, j);
            }
        }
        return ans;
    }
};