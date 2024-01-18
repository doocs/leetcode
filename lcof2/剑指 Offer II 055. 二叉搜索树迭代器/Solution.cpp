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
class BSTIterator {
public:
    vector<int> vals;
    int cur;
    BSTIterator(TreeNode* root) {
        cur = 0;
        inorder(root);
    }

    int next() {
        return vals[cur++];
    }

    bool hasNext() {
        return cur < vals.size();
    }

    void inorder(TreeNode* root) {
        if (root) {
            inorder(root->left);
            vals.push_back(root->val);
            inorder(root->right);
        }
    }
};

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator* obj = new BSTIterator(root);
 * int param_1 = obj->next();
 * bool param_2 = obj->hasNext();
 */