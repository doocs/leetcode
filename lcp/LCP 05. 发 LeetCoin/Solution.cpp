const int MOD = 1e9 + 7;

class Node {
public:
    Node* left;
    Node* right;
    int l;
    int r;
    int mid;
    int v;
    int add;

    Node(int l, int r) {
        this->l = l;
        this->r = r;
        this->mid = (l + r) >> 1;
        this->left = this->right = nullptr;
        v = add = 0;
    }
};

class SegmentTree {
private:
    Node* root;

public:
    SegmentTree(int n) {
        root = new Node(1, n);
    }

    void modify(int l, int r, int v) {
        modify(l, r, v, root);
    }

    void modify(int l, int r, int v, Node* node) {
        if (l > r) return;
        if (node->l >= l && node->r <= r) {
            node->v = (node->v + (node->r - node->l + 1) * v) % MOD;
            node->add += v;
            return;
        }
        pushdown(node);
        if (l <= node->mid) modify(l, r, v, node->left);
        if (r > node->mid) modify(l, r, v, node->right);
        pushup(node);
    }

    int query(int l, int r) {
        return query(l, r, root);
    }

    int query(int l, int r, Node* node) {
        if (l > r) return 0;
        if (node->l >= l && node->r <= r) return node->v;
        pushdown(node);
        int v = 0;
        if (l <= node->mid) v += query(l, r, node->left);
        if (r > node->mid) v += query(l, r, node->right);
        return v % MOD;
    }

    void pushup(Node* node) {
        node->v = (node->left->v + node->right->v) % MOD;
    }

    void pushdown(Node* node) {
        if (!node->left) node->left = new Node(node->l, node->mid);
        if (!node->right) node->right = new Node(node->mid + 1, node->r);
        if (node->add) {
            Node* left = node->left;
            Node* right = node->right;
            left->v = (left->v + (left->r - left->l + 1) * node->add) % MOD;
            right->v = (right->v + (right->r - right->l + 1) * node->add) % MOD;
            left->add += node->add;
            right->add += node->add;
            node->add = 0;
        }
    }
};

class Solution {
public:
    int idx;

    vector<int> bonus(int n, vector<vector<int>>& leadership, vector<vector<int>>& operations) {
        vector<vector<int>> g(n + 1);
        for (auto& l : leadership) {
            int a = l[0], b = l[1];
            g[a].push_back(b);
        }
        vector<int> begin(n + 1);
        vector<int> end(n + 1);
        idx = 1;
        dfs(1, begin, end, g);
        vector<int> ans;
        SegmentTree* tree = new SegmentTree(n);
        for (auto& op : operations) {
            int p = op[0], v = op[1];
            if (p == 1)
                tree->modify(end[v], end[v], op[2]);
            else if (p == 2)
                tree->modify(begin[v], end[v], op[2]);
            else
                ans.push_back(tree->query(begin[v], end[v]));
        }
        return ans;
    }

    void dfs(int u, vector<int>& begin, vector<int>& end, vector<vector<int>>& g) {
        begin[u] = idx;
        for (int v : g[u]) dfs(v, begin, end, g);
        end[u] = idx;
        ++idx;
    }
};