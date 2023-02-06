class WordsFrequency {
public:
    WordsFrequency(vector<string>& book) {
        for (auto& x : book) {
            ++cnt[x];
        }
    }

    int get(string word) {
        return cnt[word];
    }

private:
    unordered_map<string, int> cnt;
};

/**
 * Your WordsFrequency object will be instantiated and called as such:
 * WordsFrequency* obj = new WordsFrequency(book);
 * int param_1 = obj->get(word);
 */