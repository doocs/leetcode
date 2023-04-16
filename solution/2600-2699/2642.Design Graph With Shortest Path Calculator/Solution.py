class Graph:
    def __init__(self, n: int, edges: List[List[int]]):
        self.n = n
        self.g = [[inf] * n for _ in range(n)]
        for f, t, c in edges:
            self.g[f][t] = c

    def addEdge(self, edge: List[int]) -> None:
        f, t, c = edge
        self.g[f][t] = c

    def shortestPath(self, node1: int, node2: int) -> int:
        dist = [inf] * self.n
        dist[node1] = 0
        vis = [False] * self.n
        for _ in range(self.n):
            t = -1
            for j in range(self.n):
                if not vis[j] and (t == -1 or dist[t] > dist[j]):
                    t = j
            vis[t] = True
            for j in range(self.n):
                dist[j] = min(dist[j], dist[t] + self.g[t][j])
        return -1 if dist[node2] == inf else dist[node2]


# Your Graph object will be instantiated and called as such:
# obj = Graph(n, edges)
# obj.addEdge(edge)
# param_2 = obj.shortestPath(node1,node2)
