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
    bool leafSimilar(TreeNode* root1, TreeNode* root2) {
        vector<int> l1, l2;
        dfs(root1, l1);
        dfs(root2, l2);
        return l1 == l2;
    }

    void dfs(TreeNode* root, vector<int>& nums) {
        if (root->left == root->right) {
            nums.push_back(root->val);
            return;
        }
        if (root->left) {
            dfs(root->left, nums);
        }
        if (root->right) {
            dfs(root->right, nums);
        }
    }
};
