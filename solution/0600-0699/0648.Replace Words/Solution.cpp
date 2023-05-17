class Trie {
private:
    Trie* children[26];
    int ref;

public:
    Trie()
        : ref(-1) {
        memset(children, 0, sizeof(children));
    }

    void insert(const string& w, int i) {
        Trie* node = this;
        for (auto& c : w) {
            int idx = c - 'a';
            if (!node->children[idx]) {
                node->children[idx] = new Trie();
            }
            node = node->children[idx];
        }
        node->ref = i;
    }

    int search(const string& w) {
        Trie* node = this;
        for (auto& c : w) {
            int idx = c - 'a';
            if (!node->children[idx]) {
                return -1;
            }
            node = node->children[idx];
            if (node->ref != -1) {
                return node->ref;
            }
        }
        return -1;
    }
};

class Solution {
public:
    string replaceWords(vector<string>& dictionary, string sentence) {
        Trie* trie = new Trie();
        for (int i = 0; i < dictionary.size(); ++i) {
            trie->insert(dictionary[i], i);
        }
        stringstream ss(sentence);
        string w;
        string ans;
        while (ss >> w) {
            int idx = trie->search(w);
            ans += (idx == -1 ? w : dictionary[idx]) + " ";
        }
        ans.pop_back();
        return ans;
    }
};