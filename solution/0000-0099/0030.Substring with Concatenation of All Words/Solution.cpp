class Solution {
public:
    vector<int> findSubstring(string s, vector<string>& words) {
        unordered_map<string, int> cnt;
        for (auto& w : words) cnt[w]++;
        int subLen = words[0].size();
        int n = s.size(), m = words.size();
        vector<int> ans;
        for (int i = 0; i < subLen; ++i) {
            unordered_map<string, int> cnt1;
            int l = i, r = i;
            int t = 0;
            while (r + subLen <= n) {
                string w = s.substr(r, subLen);
                r += subLen;
                if (!cnt.count(w)) {
                    l = r;
                    t = 0;
                    cnt1.clear();
                    continue;
                }
                cnt1[w]++;
                t++;
                while (cnt1[w] > cnt[w]) {
                    string remove = s.substr(l, subLen);
                    l += subLen;
                    cnt1[remove]--;
                    --t;
                }
                if (t == m) ans.push_back(l);
            }
        }
        return ans;
    }
};