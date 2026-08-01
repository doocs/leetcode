class Solution:
    def minMoves(self, balance: List[int]) -> int:
        total_balance = sum(balance)
        if total_balance < 0:
            return -1

        n = len(balance)
        total_deficit = sum(-x for x in balance if x < 0)
        if total_deficit == 0:
            return 0

        source = n
        sink = n + 1
        num_nodes = n + 2

        graph = [[] for _ in range(num_nodes)]

        def add_edge(u, v, cap, cost):
            graph[u].append([v, cap, cost, len(graph[v])])
            graph[v].append([u, 0, -cost, len(graph[u]) - 1])

        for i in range(n):
            if balance[i] > 0:
                add_edge(source, i, balance[i], 0)
            elif balance[i] < 0:
                add_edge(i, sink, -balance[i], 0)

            add_edge(i, (i + 1) % n, inf, 1)
            add_edge(i, (i - 1 + n) % n, inf, 1)

        total_cost = 0
        current_flow = 0

        while current_flow < total_deficit:
            dist = [inf] * num_nodes
            parent_node = [-1] * num_nodes
            parent_edge = [-1] * num_nodes
            in_queue = [False] * num_nodes

            queue = deque([source])
            dist[source] = 0
            in_queue[source] = True

            while queue:
                u = queue.popleft()
                in_queue[u] = False

                for idx, (v, cap, cost, _) in enumerate(graph[u]):
                    if cap > 0 and dist[v] > dist[u] + cost:
                        dist[v] = dist[u] + cost
                        parent_node[v] = u
                        parent_edge[v] = idx
                        if not in_queue[v]:
                            queue.append(v)
                            in_queue[v] = True

            if dist[sink] == inf:
                break

            push_flow = total_deficit - current_flow
            curr = sink
            while curr != source:
                p = parent_node[curr]
                idx = parent_edge[curr]
                push_flow = min(push_flow, graph[p][idx][1])
                curr = p

            curr = sink
            while curr != source:
                p = parent_node[curr]
                idx = parent_edge[curr]
                rev_idx = graph[p][idx][3]
                graph[p][idx][1] -= push_flow
                graph[curr][rev_idx][1] += push_flow
                curr = p

            current_flow += push_flow
            total_cost += push_flow * dist[sink]

        return total_cost if current_flow == total_deficit else -1
