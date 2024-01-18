MOD = int(1e9 + 7)


class Node:
    def __init__(self, l, r):
        self.left = None
        self.right = None
        self.l = l
        self.r = r
        self.mid = (l + r) >> 1
        self.v = 0
        self.add = 0


class SegmentTree:
    def __init__(self, n):
        self.root = Node(1, n)

    def modify(self, l, r, v, node=None):
        if l > r:
            return
        if node is None:
            node = self.root
        if node.l >= l and node.r <= r:
            node.v = (node.v + (node.r - node.l + 1) * v) % MOD
            node.add += v
            return
        self.pushdown(node)
        if l <= node.mid:
            self.modify(l, r, v, node.left)
        if r > node.mid:
            self.modify(l, r, v, node.right)
        self.pushup(node)

    def query(self, l, r, node=None):
        if l > r:
            return 0
        if node is None:
            node = self.root
        if node.l >= l and node.r <= r:
            return node.v
        self.pushdown(node)
        v = 0
        if l <= node.mid:
            v += self.query(l, r, node.left)
        if r > node.mid:
            v += self.query(l, r, node.right)
        return v % MOD

    def pushup(self, node):
        node.v = (node.left.v + node.right.v) % MOD

    def pushdown(self, node):
        if node.left is None:
            node.left = Node(node.l, node.mid)
        if node.right is None:
            node.right = Node(node.mid + 1, node.r)
        if node.add:
            left, right = node.left, node.right
            left.v = (left.v + (left.r - left.l + 1) * node.add) % MOD
            right.v = (right.v + (right.r - right.l + 1) * node.add) % MOD
            left.add += node.add
            right.add += node.add
            node.add = 0


class Solution:
    def bonus(
        self, n: int, leadership: List[List[int]], operations: List[List[int]]
    ) -> List[int]:
        def dfs(u):
            nonlocal idx
            begin[u] = idx
            for v in g[u]:
                dfs(v)
            end[u] = idx
            idx += 1

        g = defaultdict(list)
        for a, b in leadership:
            g[a].append(b)
        begin = [0] * (n + 1)
        end = [0] * (n + 1)
        idx = 1
        dfs(1)
        ans = []
        tree = SegmentTree(n)
        for op in operations:
            p, v = op[:2]
            if p == 1:
                tree.modify(end[v], end[v], op[2])
            elif p == 2:
                tree.modify(begin[v], end[v], op[2])
            else:
                ans.append(tree.query(begin[v], end[v]))
        return ans
