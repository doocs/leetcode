class Solution {
public:
    int maximumWidth(vector<int>& planks) {
        unordered_map<int, int> cnt;
        for (int x : planks) {
            cnt[x]++;
        }

        unordered_map<int, int> t;
        int ans = 0;

        for (auto& [x, v1] : cnt) {
            t[x] += v1;
            ans = max(ans, t[x]);

            t[x * 2] += v1 / 2;
            ans = max(ans, t[x * 2]);

            for (auto& [y, v2] : cnt) {
                if (y > x) {
                    t[x + y] += min(v1, v2);
                    ans = max(ans, t[x + y]);
                }
            }
        }

        return ans;
    }
};