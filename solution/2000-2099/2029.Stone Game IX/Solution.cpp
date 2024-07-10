class Solution {
public:
    bool stoneGameIX(vector<int>& stones) {
        vector<int> c1(3);
        for (int x : stones) {
            ++c1[x % 3];
        }
        vector<int> c2 = {c1[0], c1[2], c1[1]};
        auto check = [](auto& cnt) -> bool {
            if (--cnt[1] < 0) {
                return false;
            }
            int r = 1 + min(cnt[1], cnt[2]) * 2 + cnt[0];
            if (cnt[1] > cnt[2]) {
                --cnt[1];
                ++r;
            }
            return r % 2 && cnt[1] != cnt[2];
        };
        return check(c1) || check(c2);
    }
};