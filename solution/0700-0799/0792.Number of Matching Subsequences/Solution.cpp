class Solution {
public:
    int numMatchingSubseq(string s, vector<string>& words) {
        vector<vector<string>> buckets(26);
        for (auto word : words) buckets[word[0] - 'a'].push_back(word);
        int res = 0;
        for (auto c : s) {
            auto old = buckets[c - 'a'];
            buckets[c - 'a'].clear();
            for (auto t : old) {
                if (t.size() == 1)
                    ++res;
                else
                    buckets[t[1] - 'a'].push_back(t.substr(1));
            }
        }
        return res;
    }
};