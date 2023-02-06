class StringIterator {
public:
    StringIterator(string compressedString) {
        int n = compressedString.size();
        int i = 0;
        while (i < n) {
            char c = compressedString[i];
            int x = 0;
            while (++i < n && isdigit(compressedString[i])) {
                x = x * 10 + (compressedString[i] - '0');
            }
            d.push_back({c, x});
        }
    }

    char next() {
        if (!hasNext()) return ' ';
        char ans = d[p].first;
        if (--d[p].second == 0) {
            ++p;
        }
        return ans;
    }

    bool hasNext() {
        return p < d.size() && d[p].second > 0;
    }

private:
    vector<pair<char, int>> d;
    int p = 0;
};

/**
 * Your StringIterator object will be instantiated and called as such:
 * StringIterator* obj = new StringIterator(compressedString);
 * char param_1 = obj->next();
 * bool param_2 = obj->hasNext();
 */