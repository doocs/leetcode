class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        ans = []
        n = len(candidates)

        def dfs(s, u, t):
            if s == target:
                ans.append(t.copy())
                return
            if s > target:
                return
            for i in range(u, n):
                c = candidates[i]
                t.append(c)
                dfs(s + c, i, t)
                t.pop()

        dfs(0, 0, [])
        return ans
