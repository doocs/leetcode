class Solution {
public:
    string mergeCharacters(string s, int k) {
        unordered_map<char, int> last;
        string ans;
        for (char c : s) {
            int cur = ans.size();
            if (last.count(c) && cur - last[c] <= k) {
                continue;
            }
            ans += c;
            last[c] = cur;
        }
        return ans;
    }
};
