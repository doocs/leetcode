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
    unordered_map<int, int> d;

    bool isEvenOddTree(TreeNode* root) {
        return dfs(root, 0);
    }

    bool dfs(TreeNode* root, int i) {
        if (!root) {
            return true;
        }
        int even = i % 2 == 0;
        int prev = d.count(i) ? d[i] : (even ? 0 : 1e7);
        if (even && (root->val % 2 == 0 || prev >= root->val)) {
            return false;
        }
        if (!even && (root->val % 2 == 1 || prev <= root->val)) {
            return false;
        }
        d[i] = root->val;
        return dfs(root->left, i + 1) && dfs(root->right, i + 1);
    }
};