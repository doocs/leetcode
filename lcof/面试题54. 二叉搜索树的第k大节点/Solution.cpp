class Solution {
public:
    int kthLargest(TreeNode* root, int k) {
        cur = k;
        inOrder(root);
        return res;
    }

private:
    int cur, res;

    void inOrder(TreeNode* root) {
        if (root) {
            inOrder(root->right);
            --cur;
            if (cur == 0) {
                res = root->val;
                return;
            }
            inOrder(root->left);
        }
    }
};
