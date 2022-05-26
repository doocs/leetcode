/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
private:
    bool isUnivalVal(TreeNode* r, int val)
    {
        if (val != r->val)
            return false ;
        
        if (r->left && !isUnivalVal(r->left, val))
            return false ;
        if (r->right && !isUnivalVal(r->right, val))
            return false ;
        
        return true ;
    }
public:
    bool isUnivalTree(TreeNode* root) {
        if (nullptr == root)
            return true ;
        return isUnivalVal(root, root->val) ;
    }
} ;