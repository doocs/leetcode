class Solution:
    def numsSameConsecDiff(self, n: int, k: int) -> List[int]:
        def dfs(x: int):
            if x >= boundary:
                ans.append(x)
                return
            last = x % 10
            if last + k <= 9:
                dfs(x * 10 + last + k)
            if last - k >= 0 and k != 0:
                dfs(x * 10 + last - k)

        ans = []
        boundary = 10 ** (n - 1)
        for i in range(1, 10):
            dfs(i)
        return ans
