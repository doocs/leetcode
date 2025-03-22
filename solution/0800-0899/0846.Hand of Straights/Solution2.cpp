class Solution {
public:
    bool isNStraightHand(vector<int>& hand, int groupSize) {
        if (hand.size() % groupSize) {
            return false;
        }
        map<int, int> mp;
        for (int x : hand) {
            ++mp[x];
        }
        while (!mp.empty()) {
            int x = mp.begin()->first;
            for (int y = x; y < x + groupSize; ++y) {
                if (!mp.contains(y)) {
                    return false;
                }
                if (--mp[y] == 0) {
                    mp.erase(y);
                }
            }
        }
        return true;
    }
};
