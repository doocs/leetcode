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
    TreeNode* prev;

    bool isValidBST(TreeNode* root) {
        prev = nullptr;
        return dfs(root);
    }

    bool dfs(TreeNode* root) {
        if (!root) return true;
        if (!dfs(root->left)) return false;
        if (prev && prev->val >= root->val) return false;
        prev = root;
        if (!dfs(root->right)) return false;
        return true;
    }
};