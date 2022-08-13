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
    bool twoSumBSTs(TreeNode* root1, TreeNode* root2, int target) {
        vector<int> vals1, vals2;
        inorder(root1, vals1);
        inorder(root2, vals2);
        int i = 0, j = vals2.size() - 1;
        while (i < vals1.size() && j >= 0) {
            int s = vals1[i] + vals2[j];
            if (s == target)
                return true;
            if (s < target)
                ++i;
            else
                --j;
        }
        return false;
    }

    void inorder(TreeNode* root, vector<int>& vals) {
        if (root) {
            inorder(root->left, vals);
            vals.push_back(root->val);
            inorder(root->right, vals);
        }
    }
};