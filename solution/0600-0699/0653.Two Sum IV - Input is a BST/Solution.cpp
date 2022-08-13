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
    unordered_set<int> nodes;

    bool findTarget(TreeNode* root, int k) {
        return find(root, k);
    }

    bool find(TreeNode* root, int k) {
        if (!root) return false;
        if (nodes.count(k - root->val)) return true;
        nodes.insert(root->val);
        return find(root->left, k) || find(root->right, k);
    }
};