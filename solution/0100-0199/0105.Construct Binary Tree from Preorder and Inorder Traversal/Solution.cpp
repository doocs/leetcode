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
    TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
        int pRight = preorder.size()-1;
        int iRight = inorder.size()-1;
        return build(preorder,inorder,0,pRight,0,iRight);
    }
    
    
    TreeNode* build(vector<int>& preorder,vector<int>& inorder,int pLeft,int pRight,int iLeft,int iRight){
        if(pLeft > pRight)return NULL;
        
        TreeNode *node = new TreeNode(preorder[pLeft]);
        int idx = iLeft;
        
        while(inorder[idx] != preorder[pLeft])idx++;
        node->left = build(preorder,inorder,pLeft+1,pLeft+idx-iLeft,iLeft,idx-1);
        node->right = build(preorder,inorder,pLeft+idx-iLeft+1,pRight,idx+1,iRight);  
        return node;
    }
};