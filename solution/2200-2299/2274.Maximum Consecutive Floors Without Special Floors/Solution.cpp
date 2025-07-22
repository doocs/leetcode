class Solution {
public:
    int maxConsecutive(int bottom, int top, vector<int>& special) {
        ranges::sort(special);
        int ans = max(special[0] - bottom, top - special.back());
        for (int i = 1; i < special.size(); ++i) {
            ans = max(ans, special[i] - special[i - 1] - 1);
        }
        return ans;
    }
};
