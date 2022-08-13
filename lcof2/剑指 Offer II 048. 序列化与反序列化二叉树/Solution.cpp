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
    // Encodes a tree to a single string.
    string serialize(TreeNode* root) {
        if (!root) return "";
        string s = "";
        preorder(root, s);
        return s;
    }

    void preorder(TreeNode* root, string& s) {
        if (!root)
            s += "# ";
        else {
            s += to_string(root->val) + " ";
            preorder(root->left, s);
            preorder(root->right, s);
        }
    }

    // Decodes your encoded data to tree.
    TreeNode* deserialize(string data) {
        if (data == "") return nullptr;
        stringstream ss(data);
        return deserialize(ss);
    }

    TreeNode* deserialize(stringstream& ss) {
        string first;
        ss >> first;
        if (first == "#") return nullptr;
        TreeNode* root = new TreeNode(stoi(first));
        root->left = deserialize(ss);
        root->right = deserialize(ss);
        return root;
    }
};

// Your Codec object will be instantiated and called as such:
// Codec ser, deser;
// TreeNode* ans = deser.deserialize(ser.serialize(root));
