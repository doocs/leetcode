/*
// Definition for a Node.
class Node {
public:
    int val;
    vector<Node*> neighbors;
    Node() {
        val = 0;
        neighbors = vector<Node*>();
    }
    Node(int _val) {
        val = _val;
        neighbors = vector<Node*>();
    }
    Node(int _val, vector<Node*> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};
*/

class Solution {
public:
    Node* cloneGraph(Node* node) {
        unordered_map<Node*, Node*> g;
        auto dfs = [&](this auto&& dfs, Node* node) -> Node* {
            if (!node) {
                return nullptr;
            }
            if (g.contains(node)) {
                return g[node];
            }
            Node* cloned = new Node(node->val);
            g[node] = cloned;
            for (auto& nxt : node->neighbors) {
                cloned->neighbors.push_back(dfs(nxt));
            }
            return cloned;
        };
        return dfs(node);
    }
};
