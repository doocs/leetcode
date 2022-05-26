class Trie {
public:
    vector<Trie*> children;
    bool isEnd;
    Trie(): children(26), isEnd(false) {}
};

class Solution {
public:
    Trie* trie;

    vector<string> findAllConcatenatedWordsInADict(vector<string>& words) {
        sort(words.begin(), words.end(), [&](const string & a, const string & b){
            return a.size() < b.size(); 
        });
        vector<string> ans;
        trie = new Trie();
        for (auto& word : words)
        {
            if (word.size() == 0) continue;
            if (dfs(word, 0)) ans.push_back(word);
            else insert(word);
        }
        return ans;
    }

    bool dfs(string word, int u) {
        Trie* node = trie;
        if (u == word.size()) return true;
        for (int i = u; i < word.size(); ++i)
        {
            int idx = word[i] - 'a';
            node = node->children[idx];
            if (!node) return false;
            if (node->isEnd && dfs(word, i + 1)) return true;
        }
        return false;
    }

    void insert(string word) {
        Trie* node = trie;
        for (char c : word)
        {
            int idx = c - 'a';
            if (!node->children[idx]) node->children[idx] = new Trie();
            node = node->children[idx];
        }
        node->isEnd = true;
    }
};