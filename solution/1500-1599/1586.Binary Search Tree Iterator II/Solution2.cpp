/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class BSTIterator {
public:
    BSTIterator(TreeNode* root) {
        vector<pair<TreeNode*, int>> stk{{root, 0}};
        while (!stk.empty()) {
            auto [node, state] = stk.back();
            stk.pop_back();
            if (!node) {
                continue;
            }
            if (state == 0) {
                stk.emplace_back(node, 1);
                stk.emplace_back(node->left, 0);
                continue;
            }
            nums.push_back(node->val);
            stk.emplace_back(node->right, 0);
        }
        n = nums.size();
    }

    bool hasNext() {
        return i < n - 1;
    }

    int next() {
        return nums[++i];
    }

    bool hasPrev() {
        return i > 0;
    }

    int prev() {
        return nums[--i];
    }

private:
    vector<int> nums;
    int i = -1;
    int n = 0;
};

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator* obj = new BSTIterator(root);
 * bool param_1 = obj->hasNext();
 * int param_2 = obj->next();
 * bool param_3 = obj->hasPrev();
 * int param_4 = obj->prev();
 */
