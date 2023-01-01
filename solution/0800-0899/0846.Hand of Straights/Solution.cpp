class Solution {
public:
    bool isNStraightHand(vector<int>& hand, int groupSize) {
        unordered_map<int, int> cnt;
        for (int& v : hand) ++cnt[v];
        sort(hand.begin(), hand.end());
        for (int& v : hand) {
            if (cnt.count(v)) {
                for (int x = v; x < v + groupSize; ++x) {
                    if (!cnt.count(x)) {
                        return false;
                    }
                    if (--cnt[x] == 0) {
                        cnt.erase(x);
                    }
                }
            }
        }
        return true;
    }
};