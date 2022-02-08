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
        unordered_map<Node*, NodeCopy*> mp;
        return dfs(root, mp);
    }

    NodeCopy* dfs(Node* root, unordered_map<Node*, NodeCopy*>& mp) {
        if (!root) return nullptr;
        if (mp.count(root)) return mp[root];
        NodeCopy* copy = new NodeCopy(root->val);
        mp[root] = copy;
        copy->left = dfs(root->left, mp);
        copy->right = dfs(root->right, mp);
        copy->random = dfs(root->random, mp);
        return copy;
    }
};