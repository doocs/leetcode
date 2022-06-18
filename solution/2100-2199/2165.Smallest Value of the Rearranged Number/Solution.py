class Solution:
    def smallestNumber(self, num: int) -> int:
        if num == 0:
            return 0
        cnt = [0] * 10
        neg = num < 0
        num = abs(num)
        while num:
            num, v = divmod(num, 10)
            cnt[v] += 1
        ans = ""
        if neg:
            for i in range(9, -1, -1):
                if cnt[i]:
                    ans += str(i) * cnt[i]
            return -int(ans)
        if cnt[0]:
            for i in range(1, 10):
                if cnt[i]:
                    ans += str(i)
                    cnt[i] -= 1
                    break
        for i in range(10):
            if cnt[i]:
                ans += str(i) * cnt[i]
        return int(ans)
