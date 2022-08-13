class Solution {
public:
    string bestHand(vector<int>& ranks, vector<char>& suits) {
        unordered_set<char> s(suits.begin(), suits.end());
        if (s.size() == 1) return "Flush";
        vector<int> cnt(20);
        for (int v : ranks)
            if (++cnt[v] >= 3) return "Three of a Kind";
        for (int v : cnt)
            if (v == 2) return "Pair";
        return "High Card";
    }
};