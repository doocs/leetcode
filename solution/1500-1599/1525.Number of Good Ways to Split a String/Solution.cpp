class Solution {
public:
    int numSplits(string s) {
        unordered_map<char, int> cnt;
        for (char& c : s) {
            ++cnt[c];
        }
        unordered_set<char> vis;
        int ans = 0;
        for (char& c : s) {
            vis.insert(c);
            if (--cnt[c] == 0) {
                cnt.erase(c);
            }
            ans += vis.size() == cnt.size();
        }
        return ans;
    }
};