class Trie {
public:
    Trie* children[26]{};
    bool isEnd = false;

    void insert(const string& w) {
        Trie* node = this;
        for (char c : w) {
            int idx = c - 'a';
            if (!node->children[idx]) {
                node->children[idx] = new Trie();
            }
            node = node->children[idx];
        }
        node->isEnd = true;
    }

    string search(const string& w) {
        Trie* node = this;
        for (int i = 0; i < w.size(); ++i) {
            int idx = w[i] - 'a';
            if (!node->children[idx]) {
                return w;
            }
            node = node->children[idx];
            if (node->isEnd) {
                return w.substr(0, i + 1);
            }
        }
        return w;
    }
};

class Solution {
public:
    string replaceWords(vector<string>& dictionary, string sentence) {
        Trie trie;
        for (auto& w : dictionary) {
            trie.insert(w);
        }

        stringstream ss(sentence);
        string word, res;
        while (ss >> word) {
            if (!res.empty()) res += " ";
            res += trie.search(word);
        }
        return res;
    }
};
