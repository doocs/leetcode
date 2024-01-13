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
    vector<TreeNode*> generateTrees(int n) {
        function<vector<TreeNode*>(int, int)> dfs = [&](int i, int j) {
            if (i > j) {
                return vector<TreeNode*>{nullptr};
            }
            vector<TreeNode*> ans;
            for (int v = i; v <= j; ++v) {
                auto left = dfs(i, v - 1);
                auto right = dfs(v + 1, j);
                for (auto l : left) {
                    for (auto r : right) {
                        ans.push_back(new TreeNode(v, l, r));
                    }
                }
            }
            return ans;
        };
        return dfs(1, n);
    }
};