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
        string res = to_string(root->val) + sep;
        res += serialize(root->left);
        res += serialize(root->right);
        return res;
    }

    // Decodes your encoded data to tree.
    TreeNode* deserialize(string data) {
        list<string> nodes;
        size_t pos = 0;
        string node;
        while ((pos = data.find(sep)) != string::npos) {
            node = data.substr(0, pos);
            nodes.push_back(node);
            data.erase(0, pos + sep.length());
        }
        return deserialize(nodes);
    }

    TreeNode* deserialize(list<string>& data) {
        if (data.empty()) return nullptr;
        string first = data.front();
        data.pop_front();
        if (first == empty) return nullptr;
        TreeNode* root = new TreeNode(stoi(first));
        root->left = deserialize(data);
        root->right = deserialize(data);
        return root;
    }
};

// Your Codec object will be instantiated and called as such:
// Codec codec;
// codec.deserialize(codec.serialize(root));