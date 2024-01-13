class Solution:
    def hanota(self, A: List[int], B: List[int], C: List[int]) -> None:
        def dfs(n, a, b, c):
            if n == 1:
                c.append(a.pop())
                return
            dfs(n - 1, a, c, b)
            c.append(a.pop())
            dfs(n - 1, b, a, c)

        dfs(len(A), A, B, C)
