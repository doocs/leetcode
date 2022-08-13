class Solution {
public:
    vector<int> deckRevealedIncreasing(vector<int>& deck) {
        if (deck.size() == 0)
            return {};

        sort(deck.begin(), deck.end());
        int len = deck.size();

        int flg = deck.at(0) - 1;
        vector<int> o(len, flg);

        int i = 0, p = 0;
        for (;;) {
            o[p] = deck.at(i);

            if (++i < len) {
                do {
                    p = (p + 1) % len;
                } while (o.at(p) != flg);
                do {
                    p = (p + 1) % len;
                } while (o.at(p) != flg);
            } else
                break;
        }

        return o;
    }
};