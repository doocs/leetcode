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
class Node {
public:
    int l, r, v;
};

class SegmentTree {
public:
    vector<Node*> tr;
    vector<int> nums;

    SegmentTree(vector<int>& nums) {
        this->nums = nums;
        int n = nums.size();
        tr.resize(n << 2);
        for (int i = 0; i < tr.size(); ++i) tr[i] = new Node();
        build(1, 1, n);
    }

    void build(int u, int l, int r) {
        tr[u]->l = l;
        tr[u]->r = r;
        if (l == r) {
            tr[u]->v = nums[l - 1];
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }

    int query(int u, int l, int r) {
        if (tr[u]->l >= l && tr[u]->r <= r) return tr[u]->v;
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        int v = 0;
        if (l <= mid) v = query(u << 1, l, r);
        if (r > mid) v = max(v, query(u << 1 | 1, l, r));
        return v;
    }

    void pushup(int u) {
        tr[u]->v = max(tr[u << 1]->v, tr[u << 1 | 1]->v);
    }
};

class Solution {
public:
    SegmentTree* tree;
    vector<int> nums;
    vector<int> d;

    TreeNode* constructMaximumBinaryTree(vector<int>& nums) {
        tree = new SegmentTree(nums);
        this->nums = nums;
        d.assign(1010, 0);
        int n = nums.size();
        for (int i = 0; i < n; ++i) d[nums[i]] = i + 1;
        return dfs(1, nums.size());
    }

    TreeNode* dfs(int l, int r) {
        if (l > r) {
            return nullptr;
        }
        int val = tree->query(1, l, r);
        TreeNode* root = new TreeNode(val);
        root->left = dfs(l, d[val] - 1);
        root->right = dfs(d[val] + 1, r);
        return root;
    }
};