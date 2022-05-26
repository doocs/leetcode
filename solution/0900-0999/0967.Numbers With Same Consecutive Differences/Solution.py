class Solution:
    def numsSameConsecDiff(self, n: int, k: int) -> List[int]:
        ans = []

        def dfs(n, k, t):
            if n == 0:
                ans.append(t)
                return
            last = t % 10
            if last + k <= 9:
                dfs(n - 1, k, t * 10 + last + k)
            if last - k >= 0 and k != 0:
                dfs(n - 1, k, t * 10 + last - k)

        for i in range(1, 10):
            dfs(n - 1, k, i)
        return ans
