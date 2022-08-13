class Solution {
public:
    bool areSentencesSimilar(string sentence1, string sentence2) {
        if (sentence1 == sentence2) return true;
        int n1 = sentence1.size(), n2 = sentence2.size();
        if (n1 == n2) return false;

        if (n1 < n2) swap(sentence1, sentence2);
        auto words1 = split(sentence1);
        auto words2 = split(sentence2);
        int i = 0, j = 0;
        n1 = words1.size(), n2 = words2.size();

        while (i < n2 && words1[i] == words2[i]) ++i;
        if (i == n2) return true;

        while (j < n2 && words1[n1 - 1 - j] == words2[n2 - 1 - j]) ++j;
        return j == n2 || i + j == n2;
    }

    vector<string> split(const string& str) {
        vector<string> words;
        int i = str.find_first_not_of(' ');
        int j = str.find_first_of(' ', i);
        while (j != string::npos) {
            words.emplace_back(str.substr(i, j - i));
            i = str.find_first_not_of(' ', j);
            j = str.find_first_of(' ', i);
        }
        words.emplace_back(str.substr(i));
        return words;
    }
};
