class Solution {
public:
    string majorityFrequencyGroup(string s) {
        vector<int> cnt(26, 0);
        for (char c : s) {
            ++cnt[c - 'a'];
        }

        unordered_map<int, string> f;
        for (int i = 0; i < 26; ++i) {
            if (cnt[i] > 0) {
                f[cnt[i]].push_back('a' + i);
            }
        }

        int mx = 0, mv = 0;
        string ans;
        for (auto& e : f) {
            int v = e.first;
            string& cs = e.second;
            if (mx < (int) cs.size() || (mx == (int) cs.size() && mv < v)) {
                mx = cs.size();
                mv = v;
                ans = cs;
            }
        }
        return ans;
    }
};
