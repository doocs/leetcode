class Solution {
public:
    int partitionString(string s) {
        unordered_set<char> ss;
        int ans = 1;
        for (char c : s) {
            if (ss.count(c)) {
                ++ans;
                ss.clear();
            }
            ss.insert(c);
        }
        return ans;
    }
};