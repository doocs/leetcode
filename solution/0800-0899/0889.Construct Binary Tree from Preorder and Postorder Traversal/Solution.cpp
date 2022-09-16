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
class Solution {
public:
    unordered_map<int, int> postMap;
    TreeNode* constructFromPrePost(vector<int>& preorder, vector<int>& postorder) {
        for (int i = 0; i < postorder.size(); i++) {
            postMap[postorder[i]] = i;
        }
        return build(preorder, 0, preorder.size() - 1, postorder, 0, postorder.size() - 1);
    }

    TreeNode* build(vector<int>& preorder, int prel, int prer, vector<int>& postorder, int postl, int postr) {
        if (prel > prer) return nullptr;
        TreeNode* root = new TreeNode(preorder[prel]);
        if (prel == prer) return root;
        int leftRootIndex = postMap[preorder[prel + 1]];
        int leftLength = leftRootIndex - postl + 1;
        root->left = build(preorder, prel + 1, prel + leftLength, postorder, postl, leftRootIndex);
        root->right = build(preorder, prel + leftLength + 1, prer, postorder, leftRootIndex + 1, postr - 1);
        return root;
    }
};
