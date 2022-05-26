class Solution:
    def equalSubstring(self, s: str, t: str, maxCost: int) -> int:
        n = len(s)
        presum = [0] * (n + 1)
        for i in range(n):
            presum[i + 1] = presum[i] + abs(ord(s[i]) - ord(t[i]))
        left, right = 0, n

        def check(l):
            i = 0
            while i + l - 1 < n:
                j = i + l - 1
                if presum[j + 1] - presum[i] <= maxCost:
                    return True
                i += 1
            return False

        while left < right:
            mid = (left + right + 1) >> 1
            if check(mid):
                left = mid
            else:
                right = mid - 1
        return left
