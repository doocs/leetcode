class Solution:
    def sortPeople(self, names: list[str], heights: list[int]) -> list[str]:
        return [name for _, name in sorted(zip(heights, names), reverse=True)]
