class Solution {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        Trie trie = new Trie();
        Arrays.sort(nums);
        int n = queries.length;
        int[] ans = new int[n];
        int[][] qs = new int[n][3];
        for (int i = 0; i < n; ++i) {
            qs[i] = new int[] {i, queries[i][0], queries[i][1]};
        }
        Arrays.sort(qs, (a, b) -> a[2] - b[2]);
        int j = 0;
        for (var q : qs) {
            int i = q[0], x = q[1], m = q[2];
            while (j < nums.length && nums[j] <= m) {
                trie.insert(nums[j++]);
            }
            ans[i] = trie.search(x);
        }
        return ans;
    }
}

class Trie {
    Trie[] children = new Trie[2];

    public void insert(int x) {
        Trie node = this;
        for (int i = 30; i >= 0; --i) {
            int v = (x >> i) & 1;
            if (node.children[v] == null) {
                node.children[v] = new Trie();
            }
            node = node.children[v];
        }
    }

    public int search(int x) {
        Trie node = this;
        int ans = 0;
        for (int i = 30; i >= 0; --i) {
            int v = (x >> i) & 1;
            if (node.children[v ^ 1] != null) {
                ans |= 1 << i;
                node = node.children[v ^ 1];
            } else if (node.children[v] != null) {
                node = node.children[v];
            } else {
                return -1;
            }
        }
        return ans;
    }
}