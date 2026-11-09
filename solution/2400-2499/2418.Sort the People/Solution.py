class Solution:
    def sortPeople(self, names: list[str], heights: list[int]) -> list[str]:
        idx = list(range(len(heights)))
        idx.sort(key=lambda i: -heights[i])
        return [names[i] for i in idx]
