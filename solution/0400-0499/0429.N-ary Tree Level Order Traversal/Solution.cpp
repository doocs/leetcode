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
    vector<vector<int>> levelOrder(Node* root) {
        vector<vector<int>> ans;
        if (!root) return ans;
        queue<Node*> q {{root}};
        while (!q.empty()) {
            vector<int> t;
            for (int n = q.size(); n > 0; --n) {
                root = q.front();
                q.pop();
                t.push_back(root->val);
                for (auto& child : root->children) q.push(child);
            }
            ans.push_back(t);
        }
        return ans;
    }
};