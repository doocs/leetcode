class Solution:
    def buttonWithLongestTime(self, events: List[List[int]]) -> int:
        ans, t = events[0]
        for (_, t1), (i, t2) in pairwise(events):
            d = t2 - t1
            if d > t or (d == t and i < ans):
                ans, t = i, d
        return ans
