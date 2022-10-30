class Solution {
public:
    string oddString(vector<string>& words) {
        unordered_map<string, vector<string>> cnt;
        for (auto& w : words) {
            string d;
            for (int i = 0; i < w.size() - 1; ++i) {
                d += (char) (w[i + 1] - w[i]);
                d += ',';
            }
            cnt[d].emplace_back(w);
        }
        for (auto& [_, v] : cnt) {
            if (v.size() == 1) {
                return v[0];
            }
        }
        return "";
    }
};