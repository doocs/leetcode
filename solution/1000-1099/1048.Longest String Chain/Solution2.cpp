class Solution {
public:
    int longestStrChain(vector<string>& words) {
        ranges::sort(words, [](const string& a, const string& b) { return a.size() < b.size(); });
        unordered_map<string, int> f;
        int ans = 0;
        for (auto& w : words) {
            int x = 1;
            for (int i = 0; i < w.size(); ++i) {
                string pred = w.substr(0, i) + w.substr(i + 1);
                x = max(x, f[pred] + 1);
            }
            f[w] = x;
            ans = max(ans, x);
        }
        return ans;
    }
};
