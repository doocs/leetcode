class Solution:
    def maximumGroups(self, grades: List[int]) -> int:
        n = len(grades)
        return bisect_right(range(n + 1), n * 2, key=lambda x: x * x + x) - 1
