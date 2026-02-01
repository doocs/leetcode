class Solution:
    def countMonobit(self, n: int) -> int:
        ans = x = 1
        i = 1
        while x <= n:
            ans += 1
            x += 1 << i
            i += 1
        return ans
