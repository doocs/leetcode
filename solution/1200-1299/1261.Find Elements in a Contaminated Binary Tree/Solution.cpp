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
class FindElements {
public:
    unordered_set<int> nodes;

    FindElements(TreeNode* root) {
        root->val = 0;
        nodes.clear();
        nodes.insert(0);
        dfs(root);
    }

    bool find(int target) {
        return nodes.count(target);
    }

    void dfs(TreeNode* root) {
        if (!root) return;
        if (root->left) {
            root->left->val = root->val * 2 + 1;
            nodes.insert(root->left->val);
        }
        if (root->right) {
            root->right->val = root->val * 2 + 2;
            nodes.insert(root->right->val);
        }
        dfs(root->left);
        dfs(root->right);
    }
};

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements* obj = new FindElements(root);
 * bool param_1 = obj->find(target);
 */