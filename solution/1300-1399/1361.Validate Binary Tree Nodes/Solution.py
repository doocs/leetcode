class Solution:
    def validateBinaryTreeNodes(
        self, n: int, leftChild: List[int], rightChild: List[int]
    ) -> bool:
        p = list(range(n))
        vis = [False] * n

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i in range(n):
            l, r = leftChild[i], rightChild[i]
            if l != -1:
                if vis[l] or find(i) == find(l):
                    return False
                p[find(i)] = find(l)
                vis[l] = True
                n -= 1
            if r != -1:
                if vis[r] or find(i) == find(r):
                    return False
                p[find(i)] = find(r)
                vis[r] = True
                n -= 1
        return n == 1
