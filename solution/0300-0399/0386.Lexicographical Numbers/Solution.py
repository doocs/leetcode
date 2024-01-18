class Solution:
    def lexicalOrder(self, n: int) -> List[int]:
        def dfs(u):
            if u > n:
                return
            ans.append(u)
            for i in range(10):
                dfs(u * 10 + i)

        ans = []
        for i in range(1, 10):
            dfs(i)
        return ans
