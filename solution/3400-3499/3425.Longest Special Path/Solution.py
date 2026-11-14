class Solution:
    def longestSpecialPath(self, edges, nums):
        n = len(nums)
        graph = [[] for _ in range(n)]

        for u, v, w in edges:
            graph[u].append((v, w))
            graph[v].append((u, w))

        last = {}
        prefix = []
        best_len = 0
        best_nodes = 1

        stack = [(False, 0, -1, 0, 0, 0)]

        while stack:
            exiting, u, parent, left, weight, old = stack.pop()
            val = nums[u]

            if exiting:
                prefix.pop()
                if old == 0:
                    last.pop(val, None)
                else:
                    last[val] = old
                continue

            prefix.append((prefix[-1] if prefix else 0) + weight)

            old = last.get(val, 0)
            left = max(left, old)
            last[val] = len(prefix)

            length = prefix[-1] - prefix[left]
            nodes = len(prefix) - left

            if length > best_len or (length == best_len and nodes < best_nodes):
                best_len = length
                best_nodes = nodes

            stack.append((True, u, parent, left, 0, old))

            for v, w in reversed(graph[u]):
                if v != parent:
                    stack.append((False, v, u, left, w, 0))

        return [best_len, best_nodes]
