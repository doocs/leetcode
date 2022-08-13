class Solution {
public:
    vector<int> p;
    bool areSentencesSimilarTwo(vector<string>& sentence1, vector<string>& sentence2, vector<vector<string>>& similarPairs) {
        if (sentence1.size() != sentence2.size())
            return false;
        int n = similarPairs.size();
        p.resize(n << 1);
        for (int i = 0; i < p.size(); ++i)
            p[i] = i;
        unordered_map<string, int> words;
        int idx = 0;
        for (auto e : similarPairs) {
            string a = e[0], b = e[1];
            if (!words.count(a))
                words[a] = idx++;
            if (!words.count(b))
                words[b] = idx++;
            p[find(words[a])] = find(words[b]);
        }
        for (int i = 0; i < sentence1.size(); ++i) {
            if (sentence1[i] == sentence2[i])
                continue;
            if (!words.count(sentence1[i]) || !words.count(sentence2[i]) || find(words[sentence1[i]]) != find(words[sentence2[i]]))
                return false;
        }
        return true;
    }

    int find(int x) {
        if (p[x] != x)
            p[x] = find(p[x]);
        return p[x];
    }
};