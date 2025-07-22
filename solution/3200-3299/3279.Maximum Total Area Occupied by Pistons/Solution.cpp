class Solution {
public:
    long long maxArea(int height, vector<int>& positions, string directions) {
        map<int, int> delta;
        int diff = 0;
        long long res = 0;

        for (int i = 0; i < positions.size(); ++i) {
            int pos = positions[i];
            char dir = directions[i];
            res += pos;

            if (dir == 'U') {
                ++diff;
                delta[height - pos] -= 2;
                delta[height * 2 - pos] += 2;
            } else {
                --diff;
                delta[pos] += 2;
                delta[height + pos] -= 2;
            }
        }

        long long ans = res;
        int pre = 0;

        for (const auto& [cur, d] : delta) {
            res += static_cast<long long>(cur - pre) * diff;
            pre = cur;
            diff += d;
            ans = max(ans, res);
        }

        return ans;
    }
};
