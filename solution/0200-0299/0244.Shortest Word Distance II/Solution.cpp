class WordDistance {
public:
    WordDistance(vector<string>& wordsDict) {
        for (int i = 0; i < wordsDict.size(); ++i) {
            d[wordsDict[i]].push_back(i);
        }
    }

    int shortest(string word1, string word2) {
        auto a = d[word1], b = d[word2];
        int i = 0, j = 0;
        int ans = INT_MAX;
        while (i < a.size() && j < b.size()) {
            ans = min(ans, abs(a[i] - b[j]));
            if (a[i] <= b[j]) {
                ++i;
            } else {
                ++j;
            }
        }
        return ans;
    }

private:
    unordered_map<string, vector<int>> d;
};

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance* obj = new WordDistance(wordsDict);
 * int param_1 = obj->shortest(word1,word2);
 */