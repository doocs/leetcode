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
    int t;

    vector<TreeNode*> splitBST(TreeNode* root, int target) {
        t = target;
        return dfs(root);
    }

    vector<TreeNode*> dfs(TreeNode* root) {
        if (!root) return {nullptr, nullptr};
        if (root->val <= t) {
            auto ans = dfs(root->right);
            root->right = ans[0];
            ans[0] = root;
            return ans;
        } else {
            auto ans = dfs(root->left);
            root->left = ans[1];
            ans[1] = root;
            return ans;
        }
    }
};