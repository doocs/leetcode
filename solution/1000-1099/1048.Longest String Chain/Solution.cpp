class Solution {
public:
    int longestStrChain(vector<string>& words) {
        sort(words.begin(), words.end(), [&](string a, string b) { return a.size() < b.size(); });
        int res = 0;
        unordered_map<string, int> map;
        for (auto word : words) {
            int x = 1;
            for (int i = 0; i < word.size(); ++i) {
                string pre = word.substr(0, i) + word.substr(i + 1);
                x = max(x, map[pre] + 1);
            }
            map[word] = x;
            res = max(res, x);
        }
        return res;
    }
};