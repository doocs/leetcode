class Solution:
    def sortItems(
        self, n: int, m: int, group: List[int], beforeItems: List[List[int]]
    ) -> List[int]:
        def topo_sort(degree, graph, items):
            q = deque(i for _, i in enumerate(items) if degree[i] == 0)
            res = []
            while q:
                i = q.popleft()
                res.append(i)
                for j in graph[i]:
                    degree[j] -= 1
                    if degree[j] == 0:
                        q.append(j)
            return res if len(res) == len(items) else []

        idx = m
        group_items = [[] for _ in range(n + m)]
        for i, g in enumerate(group):
            if g == -1:
                group[i] = idx
                idx += 1
            group_items[group[i]].append(i)

        item_degree = [0] * n
        group_degree = [0] * (n + m)
        item_graph = [[] for _ in range(n)]
        group_graph = [[] for _ in range(n + m)]
        for i, gi in enumerate(group):
            for j in beforeItems[i]:
                gj = group[j]
                if gi == gj:
                    item_degree[i] += 1
                    item_graph[j].append(i)
                else:
                    group_degree[gi] += 1
                    group_graph[gj].append(gi)

        group_order = topo_sort(group_degree, group_graph, range(n + m))
        if not group_order:
            return []
        ans = []
        for gi in group_order:
            items = group_items[gi]
            item_order = topo_sort(item_degree, item_graph, items)
            if len(items) != len(item_order):
                return []
            ans.extend(item_order)
        return ans
