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
    unordered_set<int> s;

    TreeNode* lowestCommonAncestor(TreeNode* root, vector<TreeNode*>& nodes) {
        for (auto node : nodes) s.insert(node->val);
        return dfs(root);
    }

    TreeNode* dfs(TreeNode* root) {
        if (!root || s.count(root->val)) return root;
        auto left = dfs(root->left);
        auto right = dfs(root->right);
        if (!left) return right;
        if (!right) return left;
        return root;
    }
};