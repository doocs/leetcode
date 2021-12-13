class Solution:
    def combinationSum3(self, k: int, n: int) -> List[List[int]]:
        def dfs(i, s, t):
            if i > 9 or s > n or len(t) > k:
                return
            if s == n and len(t) == k:
                ans.append(t[:])
                return
            i += 1
            t.append(i)
            dfs(i, s + i, t)
            t.pop()
            dfs(i, s, t)

        ans = []
        dfs(0, 0, [])
        return ans
