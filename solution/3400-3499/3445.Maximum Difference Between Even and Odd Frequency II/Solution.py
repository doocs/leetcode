class Solution:
    def maxDifference(self, S: str, k: int) -> int:
        s = list(map(int, S))
        ans = -inf
        for a in range(5):
            for b in range(5):
                if a == b:
                    continue
                curA = curB = 0
                preA = preB = 0
                t = [[inf, inf], [inf, inf]]
                l = -1
                for r, x in enumerate(s):
                    curA += x == a
                    curB += x == b
                    while r - l >= k and curB - preB >= 2:
                        t[preA & 1][preB & 1] = min(t[preA & 1][preB & 1], preA - preB)
                        l += 1
                        preA += s[l] == a
                        preB += s[l] == b
                    ans = max(ans, curA - curB - t[curA & 1 ^ 1][curB & 1])
        return ans
