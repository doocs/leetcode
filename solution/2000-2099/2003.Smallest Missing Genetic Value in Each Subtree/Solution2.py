class Solution:
    def smallestMissingValueSubtree(
        self, parents: List[int], nums: List[int]
    ) -> List[int]:
        def dfs(i: int) -> None:
            stk = [i]
            while stk:
                u = stk.pop()
                if vis[u]:
                    continue
                vis[u] = True
                if nums[u] < len(has):
                    has[nums[u]] = True
                for j in g[u]:
                    stk.append(j)

        n = len(nums)
        ans = [1] * n
        g = [[] for _ in range(n)]
        idx = -1
        for i, p in enumerate(parents):
            if i:
                g[p].append(i)
            if nums[i] == 1:
                idx = i
        if idx == -1:
            return ans
        vis = [False] * n
        has = [False] * (n + 2)
        i = 2
        while idx != -1:
            dfs(idx)
            while has[i]:
                i += 1
            ans[idx] = i
            idx = parents[idx]
        return ans
