class Solution:
    def meetRequirement(self, n: int, lights: List[List[int]], requirement: List[int]) -> int:
        d = [0] * 100010
        for p, r in lights:
            i, j = max(0, p - r), min(n - 1, p + r)
            d[i] += 1
            d[j + 1] -= 1
        s = ans = 0
        for i, r in enumerate(requirement):
            s += d[i]
            if s >= r:
                ans += 1
        return ans
