class Solution {
public:
    int numJewelsInStones(string jewels, string stones) {
        unordered_set<char> s;
        for (char c : jewels) {
            s.insert(c);
        }
        int res = 0;
        for (char c : stones) {
            res += s.count(c);
        }
        return res;
    }
};