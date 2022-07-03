class Solution:
    def largestInteger(self, num: int) -> int:
        cnt = Counter()
        x = num
        while x:
            x, v = divmod(x, 10)
            cnt[v] += 1
        x = num
        ans = 0
        t = 1
        while x:
            x, v = divmod(x, 10)
            for y in range(10):
                if ((v ^ y) & 1) == 0 and cnt[y]:
                    ans += y * t
                    t *= 10
                    cnt[y] -= 1
                    break
        return ans
