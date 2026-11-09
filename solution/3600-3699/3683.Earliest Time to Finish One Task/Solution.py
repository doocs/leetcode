class Solution:
    def earliestTime(self, tasks: list[list[int]]) -> int:
        return min(s + t for s, t in tasks)
