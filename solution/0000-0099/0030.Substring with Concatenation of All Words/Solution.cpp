class Solution {
public:
    vector<int> findSubstring(string s, vector<string>& words) {
        unordered_map<string, int> cnt;
        for (const auto& w : words) {
            cnt[w]++;
        }

        vector<int> ans;
        int m = s.length(), n = words.size(), k = words[0].length();

        for (int i = 0; i < k; ++i) {
            int l = i, r = i;
            unordered_map<string, int> cnt1;
            while (r + k <= m) {
                string t = s.substr(r, k);
                r += k;

                if (!cnt.contains(t)) {
                    cnt1.clear();
                    l = r;
                    continue;
                }

                cnt1[t]++;

                while (cnt1[t] > cnt[t]) {
                    string w = s.substr(l, k);
                    if (--cnt1[w] == 0) {
                        cnt1.erase(w);
                    }
                    l += k;
                }

                if (r - l == n * k) {
                    ans.push_back(l);
                }
            }
        }

        return ans;
    }
};
