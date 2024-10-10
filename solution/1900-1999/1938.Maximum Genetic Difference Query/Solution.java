import java.util.*;

class TrieNode {
    TrieNode[] children;
    int count;

    public TrieNode() {
        this.children = new TrieNode[2];
        this.count = 0;
    }
}

class Trie {
    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(int num) {
        TrieNode node = root;
        for (int i = 17; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (node.children[bit] == null) {
                node.children[bit] = new TrieNode();
            }
            node = node.children[bit];
            node.count++;
        }
    }

    public void remove(int num) {
        TrieNode node = root;
        for (int i = 17; i >= 0; i--) {
            int bit = (num >> i) & 1;
            node = node.children[bit];
            node.count--;
        }
    }

    public int maxXOR(int num) {
        TrieNode node = root;
        int max_xor = 0;
        for (int i = 17; i >= 0; i--) {
            int bit = (num >> i) & 1;
            int toggled_bit = 1 - bit;
            if (node.children[toggled_bit] != null && node.children[toggled_bit].count > 0) {
                max_xor |= (1 << i);
                node = node.children[toggled_bit];
            } else {
                node = node.children[bit];
            }
        }
        return max_xor;
    }
}

class Solution {
    public int[] maxGeneticDifference(int[] parents, int[][] queries) {
        int n = parents.length;
        
        // Build the tree as an adjacency list
        Map<Integer, List<Integer>> tree = new HashMap<>();
        int root = -1;
        
        for (int i = 0; i < n; i++) {
            if (parents[i] == -1) {
                root = i;
            } else {
                tree.computeIfAbsent(parents[i], k -> new ArrayList<>()).add(i);
            }
        }

        // Group queries by node
        Map<Integer, List<int[]>> queryMap = new HashMap<>();
        for (int i = 0; i < queries.length; i++) {
            int node = queries[i][0];
            int val = queries[i][1];
            queryMap.computeIfAbsent(node, k -> new ArrayList<>()).add(new int[]{val, i});
        }

        // Result array
        int[] res = new int[queries.length];
        
        // Trie to store and query the path genetic values
        Trie trie = new Trie();

        // Depth-first search to solve the queries
        dfs(root, tree, queryMap, trie, res);
        
        return res;
    }

    private void dfs(int node, Map<Integer, List<Integer>> tree, Map<Integer, List<int[]>> queryMap, Trie trie, int[] res) {
        trie.insert(node);

        // Handle queries for this node
        if (queryMap.containsKey(node)) {
            for (int[] query : queryMap.get(node)) {
                int val = query[0];
                int idx = query[1];
                res[idx] = trie.maxXOR(val);
            }
        }

        // Recurse for children
        if (tree.containsKey(node)) {
            for (int child : tree.get(node)) {
                dfs(child, tree, queryMap, trie, res);
            }
        }

        // Remove the node after processing its subtree
        trie.remove(node);
    }
}
