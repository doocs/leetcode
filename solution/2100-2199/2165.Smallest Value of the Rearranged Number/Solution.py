class Solution:
    def smallestNumber(self, num: int) -> int:
        neg = num < 0
        num = abs(num)
        cnt = [0] * 10
        while num:
            cnt[num % 10] += 1
            num //= 10
        ans = 0
        if neg:
            for i in reversed(range(10)):
                for _ in range(cnt[i]):
                    ans *= 10
                    ans += i
            return -ans
        if cnt[0]:
            for i in range(1, 10):
                if cnt[i]:
                    ans = i
                    cnt[i] -= 1
                    break
        for i in range(10):
            for _ in range(cnt[i]):
                ans *= 10
                ans += i
        return ans
