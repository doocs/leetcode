class Solution:
    def getFactors(self, n: int) -> List[List[int]]:
        def dfs(n, i):
            if t:
                ans.append(t + [n])
            j = i
            while j * j <= n:
                if n % j == 0:
                    t.append(j)
                    dfs(n // j, j)
                    t.pop()
                j += 1

        t = []
        ans = []
        dfs(n, 2)
        return ans
