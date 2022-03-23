class Node {
    Node left;
    Node right;
    int l;
    int r;
    int mid;
    int v;
    int add;
    public Node(int l, int r) {
        this.l = l;
        this.r = r;
        this.mid = (l + r) >> 1;
    }
}

class SegmentTree {
    private Node root;
    private static final int MOD = (int) 1e9 + 7;

    public SegmentTree(int n) {
        root = new Node(1, n);
    }

    public void modify(int l, int r, int v) {
        modify(l, r, v, root);
    }

    public void modify(int l, int r, int v, Node node) {
        if (l > r) {
            return;
        }
        if (node.l >= l && node.r <= r) {
            node.v = (node.v + (node.r - node.l + 1) * v) % MOD;
            node.add += v;
            return;
        }
        pushdown(node);
        if (l <= node.mid) {
            modify(l, r, v, node.left);
        }
        if (r > node.mid) {
            modify(l, r, v, node.right);
        }
        pushup(node);
    }

    public int query(int l, int r) {
        return query(l, r, root);
    }

    public int query(int l, int r, Node node) {
        if (l > r) {
            return 0;
        }
        if (node.l >= l && node.r <= r) {
            return node.v;
        }
        pushdown(node);
        int v = 0;
        if (l <= node.mid) {
            v = (v + query(l, r, node.left)) % MOD;
        }
        if (r > node.mid) {
            v = (v + query(l, r, node.right)) % MOD;
        }
        return v;
    }

    public void pushup(Node node) {
        node.v = (node.left.v + node.right.v) % MOD;
    }

    public void pushdown(Node node) {
        if (node.left == null) {
            node.left = new Node(node.l, node.mid);
        }
        if (node.right == null) {
            node.right = new Node(node.mid + 1, node.r);
        }
        if (node.add != 0) {
            Node left = node.left, right = node.right;
            left.v = (left.v + (left.r - left.l + 1) * node.add) % MOD;
            right.v = (right.v + (right.r - right.l + 1) * node.add) % MOD;
            left.add += node.add;
            right.add += node.add;
            node.add = 0;
        }
    }
}

class Solution {
    private List<Integer>[] g;
    private int[] begin;
    private int[] end;
    private int idx;

    public int[] bonus(int n, int[][] leadership, int[][] operations) {
        g = new List[n + 1];
        for (int i = 0; i < g.length; ++i) {
            g[i] = new ArrayList<>();
        }
        for (int[] l : leadership) {
            int a = l[0], b = l[1];
            g[a].add(b);
        }
        begin = new int[n + 1];
        end = new int[n + 1];
        idx = 1;
        dfs(1);
        List<Integer> ans = new ArrayList<>();
        SegmentTree tree = new SegmentTree(n);
        for (int[] op : operations) {
            int p = op[0], v = op[1];
            if (p == 1) {
                tree.modify(end[v], end[v], op[2]);
            } else if (p == 2) {
                tree.modify(begin[v], end[v], op[2]);
            } else {
                ans.add(tree.query(begin[v], end[v]));
            }
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    private void dfs(int u) {
        begin[u] = idx;
        for (int v : g[u]) {
            dfs(v);
        }
        end[u] = idx;
        ++idx;
    }
}