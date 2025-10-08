class Solution:
    def minTime(self, skill: List[int], mana: List[int]) -> int:
        max = lambda a, b: a if a > b else b
        n = len(skill)
        f = [0] * n
        for x in mana:
            tot = 0
            for i in range(n):
                tot = max(tot, f[i]) + skill[i] * x
            f[-1] = tot
            for i in range(n - 2, -1, -1):
                f[i] = f[i + 1] - skill[i + 1] * x
        return f[-1]
