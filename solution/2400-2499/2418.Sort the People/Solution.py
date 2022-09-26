class Solution:
    def sortPeople(self, names: List[str], heights: List[int]) -> List[str]:
        idx = list(range(len(heights)))
        idx.sort(key=lambda i: -heights[i])
        return [names[i] for i in idx]
