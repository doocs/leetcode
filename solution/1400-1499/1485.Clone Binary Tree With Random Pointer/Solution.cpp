/**
 * Definition for a Node.
 * struct Node {
 *     int val;
 *     Node *left;
 *     Node *right;
 *     Node *random;
 *     Node() : val(0), left(nullptr), right(nullptr), random(nullptr) {}
 *     Node(int x) : val(x), left(nullptr), right(nullptr), random(nullptr) {}
 *     Node(int x, Node *left, Node *right, Node *random) : val(x), left(left), right(right), random(random) {}
 * };
 */

class Solution {
public:
    NodeCopy* copyRandomBinaryTree(Node* root) {
        unordered_map<Node*, NodeCopy*> seen;
        auto dfs = [&](this auto&& dfs, Node* root) -> NodeCopy* {
            if (!root) {
                return nullptr;
            }
            if (seen.contains(root)) {
                return seen[root];
            }
            NodeCopy* copy = new NodeCopy(root->val);
            seen[root] = copy;
            copy->left = dfs(root->left);
            copy->right = dfs(root->right);
            copy->random = dfs(root->random);
            return copy;
        };
        return dfs(root);
    }
};
