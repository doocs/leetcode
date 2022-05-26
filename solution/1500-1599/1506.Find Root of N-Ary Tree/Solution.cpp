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
        int xorsum = 0;
        for (auto& node : tree) {
            xorsum ^= node->val;
            for (auto& child : node->children) {
                xorsum ^= child->val;
            }
        }
        for (auto& node : tree) {
            if (node->val == xorsum) {
                return node;
            }
        }
        return nullptr;
    }
};