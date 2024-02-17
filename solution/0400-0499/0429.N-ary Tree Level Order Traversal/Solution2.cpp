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
        function<void(Node*, int i)> dfs = [&](Node* root, int i) {
            if (!root) {
                return;
            }
            if (ans.size() <= i) {
                ans.push_back({});
            }
            ans[i++].push_back(root->val);
            for (auto& child : root->children) {
                dfs(child, i);
            }
        };
        dfs(root, 0);
        return ans;
    }
};