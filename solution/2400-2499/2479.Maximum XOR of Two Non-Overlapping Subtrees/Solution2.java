class Trie {
    Trie[] children = new Trie[2];

    void insert(long x) {
        Trie node = this;
        for (int i = 47; i >= 0; --i) {
            int v = (int) (x >> i) & 1;
            if (node.children[v] == null) {
                node.children[v] = new Trie();
            }
            node = node.children[v];
        }
    }

    long search(long x) {
        Trie node = this;
        long res = 0;
        for (int i = 47; i >= 0; --i) {
            int v = (int) (x >> i) & 1;
            if (node == null) {
                return res;
            }
            if (node.children[v ^ 1] != null) {
                res = res << 1 | 1;
                node = node.children[v ^ 1];
            } else {
                res <<= 1;
                node = node.children[v];
            }
        }
        return res;
    }
}

class Solution {
    public long maxXor(int n, int[][] edges, int[] values) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        long[] s = new long[n];
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, -1, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], fa = cur[1], state = cur[2];
            if (state == 0) {
                stk.push(new int[] {i, fa, 1});
                for (int k = g[i].size() - 1; k >= 0; --k) {
                    int j = g[i].get(k);
                    if (j != fa) {
                        stk.push(new int[] {j, i, 0});
                    }
                }
            } else {
                long t = values[i];
                for (int j : g[i]) {
                    if (j != fa) {
                        t += s[j];
                    }
                }
                s[i] = t;
            }
        }
        long ans = 0;
        Trie tree = new Trie();
        stk.push(new int[] {0, -1, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], fa = cur[1], state = cur[2];
            if (state == 0) {
                ans = Math.max(ans, tree.search(s[i]));
                stk.push(new int[] {i, fa, 1});
                for (int k = g[i].size() - 1; k >= 0; --k) {
                    int j = g[i].get(k);
                    if (j != fa) {
                        stk.push(new int[] {j, i, 0});
                    }
                }
            } else {
                tree.insert(s[i]);
            }
        }
        return ans;
    }
}
