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
    int maxPathSum(TreeNode* root) {
        int maxInt = INT_MIN;
        dfs(root,maxInt);
        return maxInt;
    }
    
    
    int dfs(TreeNode *root,int &maxInt){
        if(root == nullptr)return 0;
        
        int left = max(dfs(root->left,maxInt),0);
        int right = max(dfs(root->right,maxInt),0);
        maxInt = max(maxInt,left + right + root->val);
        return max(left,right) + root->val;
    }  
};