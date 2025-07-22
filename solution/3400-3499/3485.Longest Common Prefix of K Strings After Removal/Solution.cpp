class Solution {
public:
    struct TrieNode {
        int count = 0;
        int depth = 0;
        int children[26] = {0};
    };

    class SegmentTree {
    public:
        int n;
        vector<int> tree;
        vector<int>& globalCount;
        SegmentTree(int n, vector<int>& globalCount)
            : n(n)
            , globalCount(globalCount) {
            tree.assign(4 * (n + 1), -1);
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
            tree[idx] = max(tree[idx * 2], tree[idx * 2 + 1]);
        }
        void update(int idx, int l, int r, int pos, int newVal) {
            if (l == r) {
                tree[idx] = newVal > 0 ? l : -1;
                return;
            }
            int mid = (l + r) / 2;
            if (pos <= mid)
                update(idx * 2, l, mid, pos, newVal);
            else
                update(idx * 2 + 1, mid + 1, r, pos, newVal);
            tree[idx] = max(tree[idx * 2], tree[idx * 2 + 1]);
        }
        int query() {
            return tree[1];
        }
    };

    vector<int> longestCommonPrefix(vector<string>& words, int k) {
        int n = words.size();
        vector<int> ans(n, 0);
        if (n - 1 < k) return ans;
        vector<TrieNode> trie(1);
        for (const string& word : words) {
            int cur = 0;
            for (char c : word) {
                int idx = c - 'a';
                if (trie[cur].children[idx] == 0) {
                    trie[cur].children[idx] = trie.size();
                    trie.push_back({0, trie[cur].depth + 1});
                }
                cur = trie[cur].children[idx];
                trie[cur].count++;
            }
        }
        int maxDepth = 0;
        for (int i = 1; i < trie.size(); ++i) {
            if (trie[i].count >= k) {
                maxDepth = max(maxDepth, trie[i].depth);
            }
        }
        vector<int> globalCount(maxDepth + 1, 0);
        for (int i = 1; i < trie.size(); ++i) {
            if (trie[i].count >= k && trie[i].depth <= maxDepth) {
                globalCount[trie[i].depth]++;
            }
        }
        vector<vector<int>> fragileList(n);
        for (int i = 0; i < n; ++i) {
            int cur = 0;
            for (char c : words[i]) {
                int idx = c - 'a';
                cur = trie[cur].children[idx];
                if (trie[cur].count == k) {
                    fragileList[i].push_back(trie[cur].depth);
                }
            }
        }
        int segSize = maxDepth;
        if (segSize >= 1) {
            SegmentTree segTree(segSize, globalCount);
            for (int i = 0; i < n; ++i) {
                if (n - 1 < k) {
                    ans[i] = 0;
                } else {
                    for (int d : fragileList[i]) {
                        segTree.update(1, 1, segSize, d, globalCount[d] - 1);
                    }
                    int res = segTree.query();
                    ans[i] = res == -1 ? 0 : res;
                    for (int d : fragileList[i]) {
                        segTree.update(1, 1, segSize, d, globalCount[d]);
                    }
                }
            }
        }
        return ans;
    }
};