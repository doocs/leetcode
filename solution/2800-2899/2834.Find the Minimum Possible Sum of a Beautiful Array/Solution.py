class Solution:
    def minimumPossibleSum(self, n: int, target: int) -> int:
        vis = set()
        ans = 0
        i = 1
        for _ in range(n):
            while i in vis:
                i += 1
            ans += i
            vis.add(target - i)
            i += 1
        return ans
