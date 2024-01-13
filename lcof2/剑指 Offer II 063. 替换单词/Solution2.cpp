class Trie {
public:
    vector<Trie*> children;
    string v;
    Trie()
        : children(26)
        , v("") {}

    void insert(string word) {
        Trie* node = this;
        for (char c : word) {
            c -= 'a';
            if (!node->children[c]) node->children[c] = new Trie();
            node = node->children[c];
        }
        node->v = word;
    }

    string search(string word) {
        Trie* node = this;
        for (char c : word) {
            c -= 'a';
            if (!node->children[c]) break;
            node = node->children[c];
            if (node->v != "") return node->v;
        }
        return word;
    }
};

class Solution {
public:
    string replaceWords(vector<string>& dictionary, string sentence) {
        Trie* trie = new Trie();
        for (auto& v : dictionary) trie->insert(v);
        string ans = "";
        istringstream is(sentence);
        vector<string> ss;
        string s;
        while (is >> s) ss.push_back(s);
        for (auto word : ss) ans += trie->search(word) + " ";
        ans.pop_back();
        return ans;
    }
};