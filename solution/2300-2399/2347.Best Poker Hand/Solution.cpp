class Solution {
public:
    string bestHand(vector<int>& ranks, vector<char>& suits) {
        bool flush = true;
        for (int i = 1; i < 5 && flush; ++i) {
            flush = suits[i] == suits[i - 1];
        }
        if (flush) {
            return "Flush";
        }
        int cnt[14]{};
        bool pair = false;
        for (int& x : ranks) {
            if (++cnt[x] == 3) {
                return "Three of a Kind";
            }
            pair |= cnt[x] == 2;
        }
        return pair ? "Pair" : "High Card";
    }
};