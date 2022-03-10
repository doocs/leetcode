class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        def dfs(s, u, t):
            if s == target:
                ans.append(t[:])
                return
            if s > target:
                return
            for i in range(u, len(candidates)):
                c = candidates[i]
                t.append(c)
                dfs(s + c, i, t)
                t.pop()

        ans = []
        dfs(0, 0, [])
        return ans
