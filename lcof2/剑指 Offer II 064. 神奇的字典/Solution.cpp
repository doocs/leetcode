class MagicDictionary {
public:
    /** Initialize your data structure here. */
    MagicDictionary() {
    }

    void buildDict(vector<string> dictionary) {
        for (string word : dictionary) {
            words.insert(word);
            for (string p : patterns(word)) ++counter[p];
        }
    }

    bool search(string searchWord) {
        for (string p : patterns(searchWord)) {
            if (counter[p] > 1 || (counter[p] == 1 && !words.count(searchWord))) return true;
        }
        return false;
    }

private:
    unordered_set<string> words;
    unordered_map<string, int> counter;

    vector<string> patterns(string word) {
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