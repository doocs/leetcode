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
    vector<int> preorderTraversal(TreeNode *root) {
        vector<int> res;
        while (root)
        {
            if (root->left == nullptr)
            {
                res.push_back(root->val);
                root = root->right;
            }
            else
            {
                TreeNode *pre = root->left;
                while (pre->right && pre->right != root)
                {
                    pre = pre->right;
                }
                if (pre->right == nullptr)
                {
                    res.push_back(root->val);
                    pre->right = root;
                    root = root->left;
                }
                else
                {
                    pre->right = nullptr;
                    root = root->right;
                }
            }
        }
        return res;
    }
};