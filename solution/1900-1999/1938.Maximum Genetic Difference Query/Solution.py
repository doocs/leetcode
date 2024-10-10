from collections import defaultdict

class TrieNode:
    def __init__(self):
        self.children = {}
        self.count = 0

class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, num):
        node = self.root
        for i in range(17, -1, -1):
            bit = (num >> i) & 1
            if bit not in node.children:
                node.children[bit] = TrieNode()
            node = node.children[bit]
            node.count += 1

    def remove(self, num):
        node = self.root
        for i in range(17, -1, -1):
            bit = (num >> i) & 1
            if bit in node.children:
                node = node.children[bit]
                node.count -= 1

    def maxXOR(self, num):
        node = self.root
        max_xor = 0
        for i in range(17, -1, -1):
            bit = (num >> i) & 1
            toggled_bit = 1 - bit
            if toggled_bit in node.children and node.children[toggled_bit].count > 0:
                max_xor |= (1 << i)
                node = node.children[toggled_bit]
            else:
                node = node.children[bit]
        return max_xor

class Solution:
    def maxGeneticDifference(self, parents, queries):
        n = len(parents)
        
        # Build the tree as an adjacency list
        tree = defaultdict(list)
        root = -1
        for i in range(n):
            if parents[i] == -1:
                root = i
            else:
                tree[parents[i]].append(i)

        # Group queries by the node
        query_map = defaultdict(list)
        for idx, (node, val) in enumerate(queries):
            query_map[node].append((val, idx))
        
        # Result array
        res = [0] * len(queries)
        
        # Trie to store and query the path genetic values
        trie = Trie()

        # Depth-first search to solve the queries
        def dfs(node):
            trie.insert(node)
            if node in query_map:
                for val, idx in query_map[node]:
                    res[idx] = trie.maxXOR(val)
            for child in tree[node]:
                dfs(child)
            trie.remove(node)

        # Start DFS from the root
        dfs(root)
        
        return res
