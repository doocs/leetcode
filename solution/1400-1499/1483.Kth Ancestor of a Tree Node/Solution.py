class TreeAncestor:
    def __init__(self, n: int, parent: List[int]):
        self.p = [[-1] * 18 for _ in range(n)]
        for i, fa in enumerate(parent):
            self.p[i][0] = fa
        for i in range(n):
            for j in range(1, 18):
                if self.p[i][j - 1] == -1:
                    continue
                self.p[i][j] = self.p[self.p[i][j - 1]][j - 1]

    def getKthAncestor(self, node: int, k: int) -> int:
        for i in range(17, -1, -1):
            if k >> i & 1:
                node = self.p[node][i]
                if node == -1:
                    break
        return node


# Your TreeAncestor object will be instantiated and called as such:
# obj = TreeAncestor(n, parent)
# param_1 = obj.getKthAncestor(node,k)
