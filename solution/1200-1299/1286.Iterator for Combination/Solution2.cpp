class CombinationIterator {
public:
    int size;
    string cs;
    int curr;

    CombinationIterator(string characters, int combinationLength) {
        int n = characters.size();
        curr = (1 << n) - 1;
        reverse(characters.begin(), characters.end());
        cs = characters;
        size = combinationLength;
    }

    string next() {
        while (curr >= 0 && __builtin_popcount(curr) != size) --curr;
        string ans;
        for (int i = 0; i < cs.size(); ++i) {
            if ((curr >> i) & 1) {
                ans += cs[i];
            }
        }
        reverse(ans.begin(), ans.end());
        --curr;
        return ans;
    }

    bool hasNext() {
        while (curr >= 0 && __builtin_popcount(curr) != size) --curr;
        return curr >= 0;
    }
};

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator* obj = new CombinationIterator(characters, combinationLength);
 * string param_1 = obj->next();
 * bool param_2 = obj->hasNext();
 */