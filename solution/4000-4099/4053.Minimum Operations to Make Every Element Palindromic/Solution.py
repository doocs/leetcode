ps = [[], []]
for i in range(1, 10**5 + 1):
    s = str(i)
    t1 = s[::-1]
    t2 = s[:-1][::-1]
    x = int(s + t1)
    ps[x & 1].append(x)
    y = int(s + t2)
    ps[y & 1].append(y)
for p in ps:
    p.sort()


class Solution:
    def minOperations(self, nums: list[int]) -> int:
        ans = 0
        for x in nums:
            p = ps[x & 1]
            i = bisect_left(p, x)
            t = inf
            if i < len(p):
                t = p[i] - x
            if i:
                t = min(t, x - p[i - 1])
            ans += t // 2
        return ans
