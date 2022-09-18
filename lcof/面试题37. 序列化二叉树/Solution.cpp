/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Codec {
public:
    string empty = "#";
    string sep = ",";
    // Encodes a tree to a single string.
    string serialize(TreeNode* root) {
        if (!root) return "";
        string res = "";
        queue<TreeNode*> q;
        q.push(root);
        while (!q.empty()) {
            TreeNode* node = q.front();
            q.pop();
            if (!node) {
                res += empty + sep;
                continue;
            }
            res += to_string(node->val) + sep;
            q.push(node->left);
            q.push(node->right);
        }
        return res;
    }

    // Decodes your encoded data to tree.
    TreeNode* deserialize(string data) {
        if (data.empty()) return nullptr;
        vector<string> nodes;
        size_t pos = 0;
        string node;
        while ((pos = data.find(sep)) != string::npos) {
            node = data.substr(0, pos);
            nodes.push_back(node);
            data.erase(0, pos + sep.length());
        }
        queue<TreeNode*> q;
        TreeNode* root = new TreeNode(stoi(nodes[0]));
        q.push(root);

        for (size_t i = 1; i < nodes.size();) {
            TreeNode* front = q.front();
            q.pop();
            // 左子树
            node = nodes[i++];
            if (node != empty) {
                front->left = new TreeNode(stoi(node));
                q.push(front->left);
            }
            // 右子树
            node = nodes[i++];
            if (node != empty) {
                front->right = new TreeNode(stoi(node));
                q.push(front->right);
            }
        }
        return root;
    }
};
// Your Codec object will be instantiated and called as such:
// Codec codec;
// codec.deserialize(codec.serialize(root));