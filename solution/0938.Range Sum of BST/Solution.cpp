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
public:
    int rangeSumBST(TreeNode* root, int L, int R) {
        if (nullptr == root)
            return 0 ;
        stack<TreeNode *> s ;
        s.push(root) ;
        int sum = 0 ;
        while (!s.empty())
        {
            TreeNode *node = s.top() ;
            s.pop() ;
            
            if (nullptr == node)
                continue ;
            
            if (node->val > R)
                s.push(node->left) ;
            else if (node->val < L)
                s.push(node->right) ;
            else
            {
                sum += node->val ;
                s.push(node->left) ;
                s.push(node->right) ;
            }
        }
        
        return sum ;
    }
};

static int x = []()
{
    ios::sync_with_stdio(false); 
    cin.tie(nullptr); 
    return 0; 
}() ;
