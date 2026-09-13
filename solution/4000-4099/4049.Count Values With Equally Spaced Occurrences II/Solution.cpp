class Solution {
public:
    int countSpecialIntegers(vector<int>& nums) {
        unordered_map<int, vector<int>> g;
        for (int i = 0; i < nums.size(); i++) {
            g[nums[i]].push_back(i);
        }

        int ans = 0;
        for (auto& [x, pos] : g) {
            if (pos.size() < 3) {
                continue;
            }

            int d = pos[1] - pos[0];
            bool ok = true;
            for (int i = 1; i < pos.size(); i++) {
                if (pos[i] - pos[i - 1] != d) {
                    ok = false;
                    break;
                }
            }

            if (ok) {
                ans++;
            }
        }
        return ans;
    }
};