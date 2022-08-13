class Solution {
public:
    vector<string> commonChars(vector<string>& words) {
        vector<int> freq(26, 10000);
        for (auto word : words) {
            vector<int> t(26);
            for (char c : word)
                ++t[c - 'a'];
            for (int i = 0; i < 26; ++i)
                freq[i] = min(freq[i], t[i]);
        }
        vector<string> res;
        for (int i = 0; i < 26; i++) {
            while (freq[i]--)
                res.emplace_back(1, i + 'a');
        }
        return res;
    }
};