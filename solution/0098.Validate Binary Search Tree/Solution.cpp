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

    void traverse(TreeNode* root, vector<int> &elements){

        if( root->left != NULL )
            traverse(root->left, elements);

        elements.push_back(root->val);

        if( root->right != NULL )
            traverse(root->right, elements);
    }

    bool isValidBST(TreeNode* root) {

        if( root == NULL )
            return true;

        vector<int> elements;
        traverse(root, elements);

        for(int i = 0 ; i < elements.size() - 1 ; i++)
            if( elements[i] >= elements[i + 1] )
                return false;

        return true;
    }
};
