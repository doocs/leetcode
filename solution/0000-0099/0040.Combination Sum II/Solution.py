class Solution:
    def combinationSum2(self, candidates: List[int], target: int) -> List[List[int]]:
        def dfs(u, s, t):
            if s > target:
                return
            if s == target:
                ans.append(t[:])
                return
            for i in range(u, len(candidates)):
                if i > u and candidates[i] == candidates[i - 1]:
                    continue
                t.append(candidates[i])
                dfs(i + 1, s + candidates[i], t)
                t.pop()

        ans = []
        candidates.sort()
        dfs(0, 0, [])
        return ans
