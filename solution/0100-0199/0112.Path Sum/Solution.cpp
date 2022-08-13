class Solution {
public:
    bool hasPathSum(TreeNode* root, int sum) {

        if (root == NULL) return false;
        if (root->right == NULL && root->left == NULL && sum == root->val) return true;

        bool leftTrue = hasPathSum(root->left, sum - root->val);
        bool rightTrue = hasPathSum(root->right, sum - root->val);

        return (leftTrue || rightTrue);
    }
};