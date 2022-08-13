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
class CBTInserter {
public:
    vector<TreeNode*> tree;

    CBTInserter(TreeNode* root) {
        queue<TreeNode*> q {{root}};
        while (!q.empty()) {
            auto node = q.front();
            q.pop();
            tree.push_back(node);
            if (node->left) q.push(node->left);
            if (node->right) q.push(node->right);
        }
    }

    int insert(int v) {
        int pid = tree.size() - 1 >> 1;
        TreeNode* node = new TreeNode(v);
        tree.push_back(node);
        TreeNode* p = tree[pid];
        if (!p->left)
            p->left = node;
        else
            p->right = node;
        return p->val;
    }

    TreeNode* get_root() {
        return tree[0];
    }
};

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter* obj = new CBTInserter(root);
 * int param_1 = obj->insert(v);
 * TreeNode* param_2 = obj->get_root();
 */