class Solution:
    def addRungs(self, rungs: list[int], dist: int) -> int:
        rungs = [0] + rungs
        return sum((b - a - 1) // dist for a, b in pairwise(rungs))
