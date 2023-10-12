class Solution:
    def maxAliveYear(self, birth: List[int], death: List[int]) -> int:
        base = 1900
        d = [0] * 102
        for a, b in zip(birth, death):
            d[a - base] += 1
            d[b + 1 - base] -= 1
        s = mx = 0
        ans = 0
        for i, x in enumerate(d):
            s += x
            if mx < s:
                mx = s
                ans = base + i
        return ans
