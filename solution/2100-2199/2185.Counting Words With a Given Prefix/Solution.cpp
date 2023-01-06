class Solution {
public:
    int prefixCount(vector<string>& words, string pref) {
        int ans = 0;
        for (auto& w : words) ans += w.find(pref) == 0;
        return ans;
    }
};