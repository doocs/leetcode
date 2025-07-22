class Solution {
public:
    int minOperations(vector<int>& nums) {
        vector<int> g;
        for (int x : nums) {
            int l = 0, r = g.size();
            while (l < r) {
                int mid = (l + r) >> 1;
                if (g[mid] < x) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            if (l == g.size()) {
                g.push_back(x);
            } else {
                g[l] = x;
            }
        }
        return g.size();
    }
};