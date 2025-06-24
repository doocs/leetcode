class BinarySumTrie:
    def __init__(self):
        self.count = 0
        self.children = [None, None]

    def add(self, num: int, delta: int, bit=17):
        self.count += delta
        if bit < 0:
            return
        b = (num >> bit) & 1
        if not self.children[b]:
            self.children[b] = BinarySumTrie()
        self.children[b].add(num, delta, bit - 1)

    def collect(self, prefix=0, bit=17, output=None):
        if output is None:
            output = []
        if self.count == 0:
            return output
        if bit < 0:
            output.append(prefix)
            return output
        if self.children[0]:
            self.children[0].collect(prefix, bit - 1, output)
        if self.children[1]:
            self.children[1].collect(prefix | (1 << bit), bit - 1, output)
        return output

    def exists(self, num: int, bit=17):
        if self.count == 0:
            return False
        if bit < 0:
            return True
        b = (num >> bit) & 1
        return self.children[b].exists(num, bit - 1) if self.children[b] else False

    def find_kth(self, k: int, bit=17):
        if k > self.count:
            return -1
        if bit < 0:
            return 0
        left_count = self.children[0].count if self.children[0] else 0
        if k <= left_count:
            return self.children[0].find_kth(k, bit - 1)
        elif self.children[1]:
            return (1 << bit) + self.children[1].find_kth(k - left_count, bit - 1)
        else:
            return -1


class Solution:
    def kthSmallest(
        self, par: List[int], vals: List[int], queries: List[List[int]]
    ) -> List[int]:
        n = len(par)
        tree = [[] for _ in range(n)]
        for i in range(1, n):
            tree[par[i]].append(i)

        path_xor = vals[:]
        narvetholi = path_xor

        def compute_xor(node, acc):
            path_xor[node] ^= acc
            for child in tree[node]:
                compute_xor(child, path_xor[node])

        compute_xor(0, 0)

        node_queries = defaultdict(list)
        for idx, (u, k) in enumerate(queries):
            node_queries[u].append((k, idx))

        trie_pool = {}
        result = [0] * len(queries)

        def dfs(node):
            trie_pool[node] = BinarySumTrie()
            trie_pool[node].add(path_xor[node], 1)
            for child in tree[node]:
                dfs(child)
                if trie_pool[node].count < trie_pool[child].count:
                    trie_pool[node], trie_pool[child] = (
                        trie_pool[child],
                        trie_pool[node],
                    )
                for val in trie_pool[child].collect():
                    if not trie_pool[node].exists(val):
                        trie_pool[node].add(val, 1)
            for k, idx in node_queries[node]:
                if trie_pool[node].count < k:
                    result[idx] = -1
                else:
                    result[idx] = trie_pool[node].find_kth(k)

        dfs(0)
        return result
