class Solution:
    def hIndex(self, citations: list[int]) -> int:
        citations.sort(reverse=True)
        for h in range(len(citations), 0, -1):
            if citations[h - 1] >= h:
                return h
        return 0
