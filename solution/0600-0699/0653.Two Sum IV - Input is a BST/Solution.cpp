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

    bool find(TreeNode* node, int k) {
        if (node == nullptr) {
            return false;
        }
        if (nodes.count(k - node->val)) {
            return true;
        }
        nodes.insert(node->val);
        return find(node->left, k) || find(node->right, k);
    }
};