class Solution {
    static class TrieNode {
        int count = 0;
        int depth = 0;
        int[] children = new int[26];

        TrieNode() {
            for (int i = 0; i < 26; ++i) children[i] = -1;
        }
    }

    static class SegmentTree {
        int n;
        int[] tree;
        int[] globalCount;

        SegmentTree(int n, int[] globalCount) {
            this.n = n;
            this.globalCount = globalCount;
            this.tree = new int[4 * (n + 1)];
            for (int i = 0; i < tree.length; i++) tree[i] = -1;
            build(1, 1, n);
        }

        void build(int idx, int l, int r) {
            if (l == r) {
                tree[idx] = globalCount[l] > 0 ? l : -1;
                return;
            }
            int mid = (l + r) / 2;
            build(idx * 2, l, mid);
            build(idx * 2 + 1, mid + 1, r);
            tree[idx] = Math.max(tree[idx * 2], tree[idx * 2 + 1]);
        }

        void update(int idx, int l, int r, int pos, int newVal) {
            if (l == r) {
                tree[idx] = newVal > 0 ? l : -1;
                return;
            }
            int mid = (l + r) / 2;
            if (pos <= mid) {
                update(idx * 2, l, mid, pos, newVal);
            } else {
                update(idx * 2 + 1, mid + 1, r, pos, newVal);
            }
            tree[idx] = Math.max(tree[idx * 2], tree[idx * 2 + 1]);
        }

        int query() {
            return tree[1];
        }
    }

    public int[] longestCommonPrefix(String[] words, int k) {
        int n = words.length;
        int[] ans = new int[n];
        if (n - 1 < k) return ans;

        ArrayList<TrieNode> trie = new ArrayList<>();
        trie.add(new TrieNode());

        for (String word : words) {
            int cur = 0;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (trie.get(cur).children[idx] == -1) {
                    trie.get(cur).children[idx] = trie.size();
                    TrieNode node = new TrieNode();
                    node.depth = trie.get(cur).depth + 1;
                    trie.add(node);
                }
                cur = trie.get(cur).children[idx];
                trie.get(cur).count++;
            }
        }

        int maxDepth = 0;
        for (int i = 1; i < trie.size(); ++i) {
            if (trie.get(i).count >= k) {
                maxDepth = Math.max(maxDepth, trie.get(i).depth);
            }
        }

        int[] globalCount = new int[maxDepth + 1];
        for (int i = 1; i < trie.size(); ++i) {
            TrieNode node = trie.get(i);
            if (node.count >= k && node.depth <= maxDepth) {
                globalCount[node.depth]++;
            }
        }

        List<List<Integer>> fragileList = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            fragileList.add(new ArrayList<>());
        }

        for (int i = 0; i < n; ++i) {
            int cur = 0;
            for (char c : words[i].toCharArray()) {
                int idx = c - 'a';
                cur = trie.get(cur).children[idx];
                if (trie.get(cur).count == k) {
                    fragileList.get(i).add(trie.get(cur).depth);
                }
            }
        }

        int segSize = maxDepth;
        if (segSize >= 1) {
            SegmentTree segTree = new SegmentTree(segSize, globalCount);
            for (int i = 0; i < n; ++i) {
                if (n - 1 < k) {
                    ans[i] = 0;
                } else {
                    for (int d : fragileList.get(i)) {
                        segTree.update(1, 1, segSize, d, globalCount[d] - 1);
                    }
                    int res = segTree.query();
                    ans[i] = res == -1 ? 0 : res;
                    for (int d : fragileList.get(i)) {
                        segTree.update(1, 1, segSize, d, globalCount[d]);
                    }
                }
            }
        }

        return ans;
    }
}