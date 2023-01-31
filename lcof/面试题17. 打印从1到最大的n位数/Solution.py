class Solution:
    def printNumbers(self, n: int) -> List[int]:
        return list(range(1, 10**n))

    def print(self, n: int) -> List[str]:
        def dfs(i, j):
            if i == j:
                ans.append("".join(s))
                return
            k = 0 if i else 1
            while k < 10:
                s.append(str(k))
                dfs(i + 1, j)
                s.pop()
                k += 1

        ans = []
        s = []
        for i in range(1, n + 1):
            dfs(0, i)
        return ans
