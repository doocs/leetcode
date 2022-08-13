class MagicDictionary {
public:
    /** Initialize your data structure here. */
    MagicDictionary() {
    }

    void buildDict(vector<string> dictionary) {
        for (string word : dictionary) {
            s.insert(word);
            for (string p : gen(word)) ++cnt[p];
        }
    }

    bool search(string searchWord) {
        for (string p : gen(searchWord)) {
            if (cnt[p] > 1 || (cnt[p] == 1 && !s.count(searchWord))) return true;
        }
        return false;
    }

private:
    unordered_set<string> s;
    unordered_map<string, int> cnt;

    vector<string> gen(string word) {
        vector<string> res;
        for (int i = 0; i < word.size(); ++i) {
            char c = word[i];
            word[i] = '*';
            res.push_back(word);
            word[i] = c;
        }
        return res;
    }
};

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary* obj = new MagicDictionary();
 * obj->buildDict(dictionary);
 * bool param_2 = obj->search(searchWord);
 */