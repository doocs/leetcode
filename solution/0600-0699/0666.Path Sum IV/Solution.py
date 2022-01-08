class Solution:
    def pathSum(self, nums: List[int]) -> int:
        def dfs(node, t):
            if node not in mp:
                return
            t += mp[node]
            d, p = divmod(node, 10)
            l = (d + 1) * 10 + (p * 2) - 1
            r = l + 1
            nonlocal ans
            if l not in mp and r not in mp:
                ans += t
                return
            dfs(l, t)
            dfs(r, t)

        ans = 0
        mp = {num // 10: num % 10 for num in nums}
        dfs(11, 0)
        return ans
