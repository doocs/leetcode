class Solution:
    def validArrangement(self, pairs):
        graph = defaultdict(deque)
        degree = defaultdict(int)

        for u, v in pairs:
            graph[u].append(v)
            degree[u] += 1
            degree[v] -= 1

        start = pairs[0][0]
        for node in graph:
            if degree[node] > 0:
                start = node
                break

        path = []

        def traverse(node):
            while graph[node]:
                traverse(graph[node].popleft())
            path.append(node)

        traverse(start)
        path.reverse()
        return [[path[i], path[i + 1]] for i in range(len(path) - 1)]
