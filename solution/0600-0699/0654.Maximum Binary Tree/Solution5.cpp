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
        struct Frame {
            int l, r, side;
            TreeNode* parent;
        };
        TreeNode* root = nullptr;
        vector<Frame> stk{{1, n, 0, nullptr}};
        while (!stk.empty()) {
            auto cur = stk.back();
            stk.pop_back();
            if (cur.l > cur.r) {
                continue;
            }
            int val = tree->query(1, cur.l, cur.r);
            TreeNode* node = new TreeNode(val);
            if (cur.parent == nullptr) {
                root = node;
            } else if (cur.side == 0) {
                cur.parent->left = node;
            } else {
                cur.parent->right = node;
            }
            int i = d[val];
            stk.push_back({i + 1, cur.r, 1, node});
            stk.push_back({cur.l, i - 1, 0, node});
        }
        return root;
    }
};
