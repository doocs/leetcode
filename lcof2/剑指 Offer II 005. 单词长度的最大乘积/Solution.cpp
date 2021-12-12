class Solution {
public:
    int maxProduct(vector<string>& words) {
        vector<vector<bool>> hash(words.size(), vector<bool>(26, false));
        for (int i = 0; i < words.size(); i++)
            for (char c: words[i])
                hash[i][c - 'a'] = true;

        int res = 0;
        for (int i = 0; i < words.size(); i++) {
            for (int j = i + 1; j < words.size(); j++) {
                int k = 0;
                for (; k < 26; k++) {
                    if (hash[i][k] && hash[j][k])
                        break;
                }

                if (k == 26) {
                    int prod = words[i].size() * words[j].size();
                    res = max(res, prod);
                }
            }
        }

        return res;
    }
};