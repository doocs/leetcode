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
    vector<int> rightSideView(TreeNode* root) {
        vector<int> ans;
        auto dfs = [&](this auto&& dfs, TreeNode* root, int depth) -> void {
            if (!root) {
                return;
            }
            if (ans.size() == depth) {
                ans.push_back(root->val);
            }
            dfs(root->right, depth + 1);
            dfs(root->left, depth + 1);
        };
        dfs(root, 0);
        return ans;
    }
};
