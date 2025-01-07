class Solution:
    def dfs(self, node, adj, vis):
        if vis[node]:
            return
        vis[node] = True
        for neighbor in adj[node]:
            self.dfs(neighbor, adj, vis)

    def countComponents(self, nums, threshold):
        adj = [[] for _ in range(threshold + 1)]
        vis = [False] * (threshold + 1)
        ans = 0

        for num in nums:
            if num > threshold:
                ans += 1
                continue
            for j in range(2 * num, threshold + 1, num):
                adj[num].append(j)
                adj[j].append(num)

        for num in nums:
            if num <= threshold and not vis[num]:
                self.dfs(num, adj, vis)
                ans += 1

        return ans
