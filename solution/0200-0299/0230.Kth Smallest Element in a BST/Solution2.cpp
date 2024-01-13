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
class BST {
public:
    BST(TreeNode* root)
        : root(root) {
        count(root);
    }

    int kthSmallest(int k) {
        TreeNode* node = root;
        while (node) {
            int v = !node->left ? 0 : cnt[node->left];
            if (v == k - 1) return node->val;
            if (v < k - 1) {
                node = node->right;
                k -= (v + 1);
            } else
                node = node->left;
        }
        return 0;
    }

private:
    TreeNode* root;
    unordered_map<TreeNode*, int> cnt;

    int count(TreeNode* root) {
        if (!root) return 0;
        int n = 1 + count(root->left) + count(root->right);
        cnt[root] = n;
        return n;
    }
};

class Solution {
public:
    int kthSmallest(TreeNode* root, int k) {
        BST bst(root);
        return bst.kthSmallest(k);
    }
};