class Solution {
public:
    int minNumBooths(vector<string>& demand) {
        int cnt[26]{};
        int ans = 0;
        for (auto& s : demand) {
            for (char& c : s) {
                if (cnt[c - 'a']) {
                    --cnt[c - 'a'];
                } else {
                    ++ans;
                }
            }
            for (char& c : s) {
                ++cnt[c - 'a'];
            }
        }
        return ans;
    }
};