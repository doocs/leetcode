class Solution {
public:
    bool stoneGameIX(vector<int>& stones) {
        vector<int> c(3);
        for (int s : stones) ++c[s % 3];
        vector<int> t = {c[0], c[2], c[1]};
        return check(c) || check(t);
    }

    bool check(vector<int>& c) {
        if (c[1] == 0) return false;
        --c[1];
        int turn = 1 + min(c[1], c[2]) * 2 + c[0];
        if (c[1] > c[2]) {
            --c[1];
            ++turn;
        }
        return turn % 2 == 1 && c[1] != c[2];
    }
};