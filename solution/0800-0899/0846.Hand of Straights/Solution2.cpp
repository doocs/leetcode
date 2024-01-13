class Solution {
public:
    bool isNStraightHand(vector<int>& hand, int groupSize) {
        if (hand.size() % groupSize != 0) return false;
        map<int, int> mp;
        for (int& h : hand) mp[h] += 1;
        while (!mp.empty()) {
            int v = mp.begin()->first;
            for (int i = v; i < v + groupSize; ++i) {
                if (!mp.count(i)) return false;
                if (mp[i] == 1)
                    mp.erase(i);
                else
                    mp[i] -= 1;
            }
        }
        return true;
    }
};