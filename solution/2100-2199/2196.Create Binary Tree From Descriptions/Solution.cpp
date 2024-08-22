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
    TreeNode* createBinaryTree(vector<vector<int>>& descriptions) {
        unordered_map<int, TreeNode*> nodes;
        unordered_set<int> children;
        for (const auto& d : descriptions) {
            int parent = d[0], child = d[1], isLeft = d[2];
            if (!nodes.contains(parent)) {
                nodes[parent] = new TreeNode(parent);
            }
            if (!nodes.contains(child)) {
                nodes[child] = new TreeNode(child);
            }
            if (isLeft) {
                nodes[parent]->left = nodes[child];
            } else {
                nodes[parent]->right = nodes[child];
            }
            children.insert(child);
        }
        for (const auto& [k, v] : nodes) {
            if (!children.contains(k)) {
                return v;
            }
        }
        return nullptr;
    }
};