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
        int ans = 0;
        unordered_map<Node*, int> height;
        vector<pair<Node*, int>> stk{{root, 0}};
        while (!stk.empty()) {
            auto [node, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                stk.push_back({node, 1});
                for (int i = (int) node->children.size() - 1; i >= 0; --i) {
                    Node* child = node->children[i];
                    if (child) {
                        stk.push_back({child, 0});
                    }
                }
            } else {
                int m1 = 0, m2 = 0;
                for (Node* child : node->children) {
                    int t = child ? height[child] : 0;
                    if (t > m1) {
                        m2 = m1;
                        m1 = t;
                    } else if (t > m2) {
                        m2 = t;
                    }
                }
                ans = max(ans, m1 + m2);
                height[node] = m1 + 1;
            }
        }
        return ans;
    }
};
