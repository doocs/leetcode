class Solution {
public:
    bool verifyPostorder(vector<int>& postorder) {
        function<bool(int, int)> dfs = [&](int l, int r) -> bool {
            if (l >= r) {
                return true;
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
            return dfs(l, i - 1) && dfs(i, r - 1);
        };
        return dfs(0, postorder.size() - 1);
    }
};