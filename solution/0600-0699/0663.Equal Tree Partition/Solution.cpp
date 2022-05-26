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
    vector<int> seen;

    bool checkEqualTree(TreeNode* root) {
        int s = sum(root);
        if (s % 2 != 0) return false;
        seen.pop_back();
        return count(seen.begin(), seen.end(), s / 2);
    }

    int sum(TreeNode* root) {
        if (!root) return 0;
        int l = sum(root->left), r = sum(root->right);
        int s = l + r + root->val;
        seen.push_back(s);
        return s;
    }
};