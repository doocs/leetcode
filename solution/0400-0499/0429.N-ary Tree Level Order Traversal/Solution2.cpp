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
        dfs(root, 0, ans);
        return ans;
    }

    void dfs(Node* root, int i, vector<vector<int>>& ans) {
        if (!root) return;
        if (ans.size() <= i) ans.push_back({});
        ans[i++].push_back(root->val);
        for (Node* child : root->children) dfs(child, i, ans);
    }
};