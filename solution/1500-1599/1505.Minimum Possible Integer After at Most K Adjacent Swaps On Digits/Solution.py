class BinaryIndexedTree:
    def __init__(self, n):
        self.n = n
        self.c = [0] * (n + 1)

    @staticmethod
    def lowbit(x):
        return x & -x

    def update(self, x, delta):
        while x <= self.n:
            self.c[x] += delta
            x += BinaryIndexedTree.lowbit(x)

    def query(self, x):
        s = 0
        while x:
            s += self.c[x]
            x -= BinaryIndexedTree.lowbit(x)
        return s


class Solution:
    def minInteger(self, num: str, k: int) -> str:
        pos = defaultdict(deque)
        for i, v in enumerate(num, 1):
            pos[int(v)].append(i)
        ans = []
        n = len(num)
        tree = BinaryIndexedTree(n)
        for i in range(1, n + 1):
            for v in range(10):
                q = pos[v]
                if q:
                    j = q[0]
                    dist = tree.query(n) - tree.query(j) + j - i
                    if dist <= k:
                        k -= dist
                        q.popleft()
                        ans.append(str(v))
                        tree.update(j, 1)
                        break
        return ''.join(ans)
