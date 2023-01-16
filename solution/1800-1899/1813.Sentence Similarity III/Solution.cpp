class Solution {
public:
    bool areSentencesSimilar(string sentence1, string sentence2) {
        auto words1 = split(sentence1, ' ');
        auto words2 = split(sentence2, ' ');
        if (words1.size() < words2.size()) {
            swap(words1, words2);
        }
        int m = words1.size(), n = words2.size();
        int i = 0, j = 0;
        while (i < n && words1[i] == words2[i]) {
            ++i;
        }
        while (j < n && words1[m - 1 - j] == words2[n - 1 - j]) {
            ++j;
        }
        return i + j >= n;
    }

    vector<string> split(string& s, char delim) {
        stringstream ss(s);
        string item;
        vector<string> res;
        while (getline(ss, item, delim)) {
            res.emplace_back(item);
        }
        return res;
    }
};