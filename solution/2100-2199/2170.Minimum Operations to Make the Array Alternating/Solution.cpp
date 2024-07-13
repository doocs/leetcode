class Solution {
public:
    int minimumOperations(vector<int>& nums) {
        auto f = [&](int i) -> vector<int> {
            int k1 = 0, k2 = 0;
            unordered_map<int, int> cnt;
            for (; i < nums.size(); i += 2) {
                cnt[nums[i]]++;
            }
            for (auto& [k, v] : cnt) {
                if (!k1 || cnt[k1] < v) {
                    k2 = k1;
                    k1 = k;
                } else if (!k2 || cnt[k2] < v) {
                    k2 = k;
                }
            }
            return {k1, !k1 ? 0 : cnt[k1], k2, !k2 ? 0 : cnt[k2]};
        };
        vector<int> a = f(0);
        vector<int> b = f(1);
        int n = nums.size();
        if (a[0] != b[0]) {
            return n - (a[1] + b[1]);
        }
        return n - max(a[1] + b[3], a[3] + b[1]);
    }
};