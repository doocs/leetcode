class BinarySumTrie {
    int count;
    BinarySumTrie[] children = new BinarySumTrie[2];

    void add(int num, int delta, int bit) {
        count += delta;
        if (bit < 0) {
            return;
        }
        int b = (num >> bit) & 1;
        if (children[b] == null) {
            children[b] = new BinarySumTrie();
        }
        children[b].add(num, delta, bit - 1);
    }

    void collect(int prefix, int bit, List<Integer> output) {
        if (count == 0) {
            return;
        }
        if (bit < 0) {
            output.add(prefix);
            return;
        }
        if (children[0] != null) {
            children[0].collect(prefix, bit - 1, output);
        }
        if (children[1] != null) {
            children[1].collect(prefix | (1 << bit), bit - 1, output);
        }
    }

    boolean exists(int num, int bit) {
        if (count == 0) {
            return false;
        }
        if (bit < 0) {
            return true;
        }
        int b = (num >> bit) & 1;
        return children[b] != null && children[b].exists(num, bit - 1);
    }

    int findKth(int k, int bit) {
        if (k > count) {
            return -1;
        }
        if (bit < 0) {
            return 0;
        }
        int leftCount = children[0] == null ? 0 : children[0].count;
        if (k <= leftCount) {
            return children[0].findKth(k, bit - 1);
        }
        if (children[1] != null) {
            return (1 << bit) + children[1].findKth(k - leftCount, bit - 1);
        }
        return -1;
    }
}

class Solution {
    private static final int BITS = 17;

    public int[] kthSmallest(int[] par, int[] vals, int[][] queries) {
        int n = par.length;
        List<Integer>[] tree = new List[n];
        Arrays.setAll(tree, i -> new ArrayList<>());
        for (int i = 1; i < n; ++i) {
            tree[par[i]].add(i);
        }
        int[] pathXor = vals.clone();
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int node = cur[0], acc = cur[1];
            pathXor[node] ^= acc;
            List<Integer> children = tree[node];
            for (int i = children.size() - 1; i >= 0; --i) {
                stk.push(new int[] {children.get(i), pathXor[node]});
            }
        }

        List<int[]>[] nodeQueries = new List[n];
        Arrays.setAll(nodeQueries, i -> new ArrayList<>());
        for (int i = 0; i < queries.length; ++i) {
            nodeQueries[queries[i][0]].add(new int[] {queries[i][1], i});
        }

        BinarySumTrie[] pool = new BinarySumTrie[n];
        int[] result = new int[queries.length];
        stk.clear();
        stk.push(new int[] {0, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int node = cur[0], state = cur[1];
            if (state == 0) {
                pool[node] = new BinarySumTrie();
                pool[node].add(pathXor[node], 1, BITS);
                stk.push(new int[] {node, 1});
                List<Integer> children = tree[node];
                for (int i = children.size() - 1; i >= 0; --i) {
                    stk.push(new int[] {children.get(i), 0});
                }
                continue;
            }
            for (int child : tree[node]) {
                if (pool[node].count < pool[child].count) {
                    BinarySumTrie tmp = pool[node];
                    pool[node] = pool[child];
                    pool[child] = tmp;
                }
                List<Integer> got = new ArrayList<>();
                pool[child].collect(0, BITS, got);
                for (int val : got) {
                    if (!pool[node].exists(val, BITS)) {
                        pool[node].add(val, 1, BITS);
                    }
                }
            }
            for (int[] q : nodeQueries[node]) {
                result[q[1]] = pool[node].count < q[0] ? -1 : pool[node].findKth(q[0], BITS);
            }
        }
        return result;
    }
}
