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
    int diameter(Node* root) {
        if (!root) {
            return 0;
        }
        unordered_map<Node*, vector<Node*>> g;
        unordered_set<Node*> seen{root};
        vector<Node*> stk{root};
        while (!stk.empty()) {
            Node* u = stk.back();
            stk.pop_back();
            for (Node* child : u->children) {
                if (!child || seen.count(child)) {
                    continue;
                }
                seen.insert(child);
                g[u].push_back(child);
                g[child].push_back(u);
                stk.push_back(child);
            }
        }
        auto farthest = [&](Node* start) {
            unordered_set<Node*> vis{start};
            vector<pair<Node*, int>> walk{{start, 0}};
            int best = 0;
            Node* node = start;
            while (!walk.empty()) {
                auto [u, t] = walk.back();
                walk.pop_back();
                if (t > best) {
                    best = t;
                    node = u;
                }
                for (Node* v : g[u]) {
                    if (!vis.count(v)) {
                        vis.insert(v);
                        walk.push_back({v, t + 1});
                    }
                }
            }
            return pair<int, Node*>{best, node};
        };
        Node* nxt = farthest(root).second;
        return farthest(nxt).first;
    }
};
