class Trie {
public:
    Trie()
        : cnt(0) {
        fill(children.begin(), children.end(), nullptr);
    }

    void insert(const string& w) {
        Trie* node = this;
        for (char c : w) {
            int idx = c - 'a';
            if (node->children[idx] == nullptr) {
                node->children[idx] = new Trie();
            }
            node = node->children[idx];
            ++node->cnt;
        }
    }

    int search(const string& w) {
        Trie* node = this;
        int ans = 0;
        for (char c : w) {
            ++ans;
            int idx = c - 'a';
            node = node->children[idx];
            if (node->cnt == 1) {
                return ans;
            }
        }
        return w.size();
    }

private:
    array<Trie*, 26> children;
    int cnt;
};

class Solution {
public:
    vector<string> wordsAbbreviation(vector<string>& words) {
        map<pair<int, int>, Trie*> tries;
        for (const auto& w : words) {
            pair<int, int> key = {static_cast<int>(w.size()), w.back() - 'a'};
            if (tries.find(key) == tries.end()) {
                tries[key] = new Trie();
            }
            tries[key]->insert(w);
        }

        vector<string> ans;
        for (const auto& w : words) {
            int m = w.size();
            pair<int, int> key = {m, w.back() - 'a'};
            int cnt = tries[key]->search(w);
            ans.push_back((cnt + 2 >= m) ? w : w.substr(0, cnt) + to_string(m - cnt - 1) + w.back());
        }

        return ans;
    }
};