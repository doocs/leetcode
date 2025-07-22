class Solution {
public:
    bool isNStraightHand(vector<int>& hand, int groupSize) {
        if (hand.size() % groupSize) {
            return false;
        }
        ranges::sort(hand);
        unordered_map<int, int> cnt;
        for (int x : hand) {
            ++cnt[x];
        }
        for (int x : hand) {
            if (cnt.contains(x)) {
                for (int y = x; y < x + groupSize; ++y) {
                    if (!cnt.contains(y)) {
                        return false;
                    }
                    if (--cnt[y] == 0) {
                        cnt.erase(y);
                    }
                }
            }
        }
        return true;
    }
};
