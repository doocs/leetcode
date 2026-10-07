class Solution {
public:
    bool verifyPostorder(vector<int>& postorder) {
        int n = postorder.size();
        vector<pair<int, int>> stk{{0, n - 1}};
        while (!stk.empty()) {
            auto [l, r] = stk.back();
            stk.pop_back();
            if (l >= r) {
                continue;
            }
            int v = postorder[r];
            int i = l;
            while (i < r && postorder[i] < v) {
                ++i;
            }
            for (int j = i; j < r; ++j) {
                if (postorder[j] < v) {
                    return false;
                }
            }
            stk.emplace_back(i, r - 1);
            stk.emplace_back(l, i - 1);
        }
        return true;
    }
};
