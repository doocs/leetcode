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
    bool IsSameTree(TreeNode *s, TreeNode *t)
    {
        if (nullptr == s && nullptr == t)
            return true ;
        if (nullptr == s || nullptr == t
           || s->val != t->val)
            return false ;
            
        return IsSameTree(s->left, t->left) 
            && IsSameTree(s->right, t->right) ;
    }
public:
    bool isSubtree(TreeNode* s, TreeNode* t) {
        if (nullptr == s)
            return false ;
        
        if (s->val == t->val && IsSameTree(s, t))
            return true ;
        
        if (!isSubtree(s->left, t) 
            && !isSubtree(s->right, t))
            return false ;
        
        return true ;
    }
};