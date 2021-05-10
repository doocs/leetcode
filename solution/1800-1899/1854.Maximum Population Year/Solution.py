class Solution:
    def maximumPopulation(self, logs: List[List[int]]) -> int:
        offset = 1950
        delta = [0] * 101
        for birth, death in logs:
            delta[birth - offset] += 1
            delta[death - offset] -= 1
        mx = cur = res = 0
        for i in range(101):
            cur += delta[i]
            if mx < cur:
                mx = cur
                res = i
        return res + offset
