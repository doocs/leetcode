class Solution:
    def average(self, salary: list[int]) -> float:
        s = sum(salary) - min(salary) - max(salary)
        return s / (len(salary) - 2)
