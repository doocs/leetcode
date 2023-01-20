class Solution:
    def minSideJumps(self, obstacles: List[int]) -> int:
        f = [1, 0, 1]
        for v in obstacles[1:]:
            for j in range(3):
                if v == j + 1:
                    f[j] = inf
                    break
            x = min(f) + 1
            for j in range(3):
                if v != j + 1:
                    f[j] = min(f[j], x)
        return min(f)
