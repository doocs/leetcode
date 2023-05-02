/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    vector<TreeNode*> allPossibleFBT(int n) {
        vector<vector<TreeNode*>> f(n + 1);
        function<vector<TreeNode*>(int)> dfs = [&](int n) -> vector<TreeNode*> {
            if (f[n].size()) {
                return f[n];
            }
            if (n == 1) {
                return vector<TreeNode*>{new TreeNode()};
            }
            vector<TreeNode*> ans;
            for (int i = 0; i < n - 1; ++i) {
                int j = n - 1 - i;
                for (auto left : dfs(i)) {
                    for (auto right : dfs(j)) {
                        ans.push_back(new TreeNode(0, left, right));
                    }
                }
            }
            return f[n] = ans;
        };
        return dfs(n);
    }
};