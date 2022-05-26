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
    TreeNode* searchBST(TreeNode* root, int val) {

        TreeNode* temp = root;

        while( temp != NULL ){
            if( temp->val == val ){
                return temp;
            }
            else if( val < temp->val ){
                temp = temp->left;
            }
            else{
                temp = temp->right;
            }
        }

        return NULL;
    }
};
