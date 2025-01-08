class DSU:
    def __init__(self, n):
        self.parent = {i: i for i in range(n)}
        self.rank = {i: 0 for i in range(n)}

    def make_set(self, v):
        self.parent[v] = v
        self.rank[v] = 1

    def find(self, x):
        if self.parent[x] != x:
            self.parent[x] = self.find(self.parent[x])
        return self.parent[x]

    def union_set(self, u, v):
        u = self.find(u)
        v = self.find(v)
        if u != v:
            if self.rank[u] < self.rank[v]:
                u, v = v, u
            self.parent[v] = u
            if self.rank[u] == self.rank[v]:
                self.rank[u] += 1


class Solution:
    def countComponents(self, nums, threshold):
        dsu = DSU(threshold + 1)

        for num in nums:
            for j in range(num, threshold + 1, num):
                dsu.union_set(num, j)

        unique_parents = set()
        for num in nums:
            if num > threshold:
                unique_parents.add(num)
            else:
                unique_parents.add(dsu.find(num))

        return len(unique_parents)
