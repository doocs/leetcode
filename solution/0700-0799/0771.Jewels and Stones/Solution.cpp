class Solution {
public:
    int numJewelsInStones(string jewels, string stones) {
        unordered_set<char> jewelsSet;
        for (int i = 0; i < jewels.length(); ++i) {
            jewelsSet.insert(jewels[i]);
        }
        int res = 0;
        for (int i = 0; i < stones.length(); ++i) {
            res += jewelsSet.count(stones[i]);
        }
        return res;
    }
};