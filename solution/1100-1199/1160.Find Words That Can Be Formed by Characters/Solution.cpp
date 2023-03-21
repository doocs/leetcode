class Solution {
public:
    int countCharacters(vector<string>& words, string chars) {
        int cnt[26]{};
        for (char& c : chars) {
            ++cnt[c - 'a'];
        }
        int ans = 0;
        for (auto& w : words) {
            int wc[26]{};
            bool ok = true;
            for (auto& c : w) {
                int i = c - 'a';
                if (++wc[i] > cnt[i]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans += w.size();
            }
        }
        return ans;
    }
};