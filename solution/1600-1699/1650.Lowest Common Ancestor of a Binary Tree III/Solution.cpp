/*
// Definition for a Node.
class Node {
public:
    int val;
    Node* left;
    Node* right;
    Node* parent;
};
*/

class Solution {
public:
    Node* lowestCommonAncestor(Node* p, Node* q) {
        unordered_set<Node*> vis;
        for (Node* node = p; node; node = node->parent) {
            vis.insert(node);
        }
        for (Node* node = q;; node = node->parent) {
            if (vis.count(node)) {
                return node;
            }
        }
    }
};