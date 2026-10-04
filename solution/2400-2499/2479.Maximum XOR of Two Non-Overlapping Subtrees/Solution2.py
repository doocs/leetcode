class Trie:
    def __init__(self):
        self.children = [None] * 2

    def insert(self, x):
        node = self
        for i in range(47, -1, -1):
            v = (x >> i) & 1
            if node.children[v] is None:
                node.children[v] = Trie()
            node = node.children[v]

    def search(self, x):
        node = self
        res = 0
        for i in range(47, -1, -1):
            v = (x >> i) & 1
            if node is None:
                return res
            if node.children[v ^ 1]:
                res = res << 1 | 1
                node = node.children[v ^ 1]
            else:
                res <<= 1
                node = node.children[v]
        return res


class Solution:
    def maxXor(self, n: int, edges: List[List[int]], values: List[int]) -> int:
        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        s = [0] * n
        stk = [(0, -1, 0)]
        while stk:
            i, fa, state = stk.pop()
            if state == 0:
                stk.append((i, fa, 1))
                for j in reversed(g[i]):
                    if j != fa:
                        stk.append((j, i, 0))
            else:
                t = values[i]
                for j in g[i]:
                    if j != fa:
                        t += s[j]
                s[i] = t
        ans = 0
        tree = Trie()
        stk = [(0, -1, 0)]
        while stk:
            i, fa, state = stk.pop()
            if state == 0:
                ans = max(ans, tree.search(s[i]))
                stk.append((i, fa, 1))
                for j in reversed(g[i]):
                    if j != fa:
                        stk.append((j, i, 0))
            else:
                tree.insert(s[i])
        return ans
