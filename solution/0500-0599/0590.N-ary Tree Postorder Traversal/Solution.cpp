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
    vector<int> postorder(Node* root) {
        vector<int> ans;
        dfs(root, ans);
        return ans;
    }

    void dfs(Node* root, vector<int>& ans) {
        if (!root) return;
        for (auto& child : root->children) dfs(child, ans);
        ans.push_back(root->val);
    }
};