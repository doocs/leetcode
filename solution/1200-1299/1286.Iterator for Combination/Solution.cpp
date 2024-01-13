class CombinationIterator {
public:
    string characters;
    vector<string> cs;
    int idx;
    int n;
    int combinationLength;
    string t;

    CombinationIterator(string characters, int combinationLength) {
        idx = 0;
        n = characters.size();
        this->characters = characters;
        this->combinationLength = combinationLength;
        dfs(0);
    }

    string next() {
        return cs[idx++];
    }

    bool hasNext() {
        return idx < cs.size();
    }

    void dfs(int i) {
        if (t.size() == combinationLength) {
            cs.push_back(t);
            return;
        }
        if (i == n) return;
        t.push_back(characters[i]);
        dfs(i + 1);
        t.pop_back();
        dfs(i + 1);
    }
};

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator* obj = new CombinationIterator(characters, combinationLength);
 * string param_1 = obj->next();
 * bool param_2 = obj->hasNext();
 */