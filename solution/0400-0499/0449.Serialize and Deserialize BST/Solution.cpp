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
        if (!root) {
            return "";
        }
        string data = "";
        function<void(TreeNode*)> dfs = [&](TreeNode* root) {
            if (!root) {
                return;
            }
            data += to_string(root->val) + " ";
            dfs(root->left);
            dfs(root->right);
        };
        dfs(root);
        data.pop_back();
        return data;
    }

    // Decodes your encoded data to tree.
    TreeNode* deserialize(string data) {
        if (data.empty()) {
            return nullptr;
        }
        vector<int> nums = split(data, ' ');
        int i = 0;
        function<TreeNode*(int, int)> dfs = [&](int mi, int mx) -> TreeNode* {
            if (i == nums.size() || nums[i] < mi || nums[i] > mx) {
                return nullptr;
            }
            int x = nums[i++];
            TreeNode* root = new TreeNode(x);
            root->left = dfs(mi, x);
            root->right = dfs(x, mx);
            return root;
        };
        return dfs(INT_MIN, INT_MAX);
    }

    vector<int> split(const string& s, char delim) {
        vector<int> tokens;
        stringstream ss(s);
        string token;
        while (getline(ss, token, delim)) {
            tokens.push_back(stoi(token));
        }
        return tokens;
    }
};

// Your Codec object will be instantiated and called as such:
// Codec* ser = new Codec();
// Codec* deser = new Codec();
// string tree = ser->serialize(root);
// TreeNode* ans = deser->deserialize(tree);
// return ans;