/*
// Definition for a Node.
class Node {
public:
    int val;
    vector<Node*> children;

    Node() {}

    Node(int _val) {
        val = _val;
    }

    Node(int _val, vector<Node*> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
public:
    Node* findRoot(vector<Node*> tree) {
        int x = 0;
        for (Node* node : tree) {
            x ^= node->val;
            for (Node* child : node->children) {
                x ^= child->val;
            }
        }
        for (int i = 0;; ++i) {
            if (tree[i]->val == x) {
                return tree[i];
            }
        }
    }
};