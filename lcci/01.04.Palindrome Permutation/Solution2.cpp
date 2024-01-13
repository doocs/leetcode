class Solution {
public:
    bool canPermutePalindrome(string s) {
        unordered_set<char> vis;
        for (auto& c : s) {
            if (vis.count(c)) {
                vis.erase(c);
            } else {
                vis.insert(c);
            }
        }
        return vis.size() < 2;
    }
};