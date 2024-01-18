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
        if (!root) return empty + sep;
        string res = "";
        res += serialize(root->left);
        res += serialize(root->right);
        res += to_string(root->val) + sep;
        return res;
    }

    // Decodes your encoded data to tree.
    TreeNode* deserialize(string data) {
        vector<string> nodes;
        size_t pos = 0;
        string node;
        while ((pos = data.find(sep)) != string::npos) {
            node = data.substr(0, pos);
            nodes.push_back(node);
            data.erase(0, pos + sep.length());
        }
        return deserialize(nodes);
    }

    TreeNode* deserialize(vector<string>& nodes) {
        if (nodes.empty()) return nullptr;
        string front = nodes.back();
        nodes.pop_back();
        if (front == empty) return nullptr;
        TreeNode* root = new TreeNode(stoi(front));
        // 先构造右子树，后构造左子树
        root->right = deserialize(nodes);
        root->left = deserialize(nodes);
        return root;
    }
};

// Your Codec object will be instantiated and called as such:
// Codec codec;
// codec.deserialize(codec.serialize(root));