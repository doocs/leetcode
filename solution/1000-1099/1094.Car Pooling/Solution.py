class Solution:
    def carPooling(self, trips: List[List[int]], capacity: int) -> bool:
        d = [0] * 1001
        for x, f, t in trips:
            d[f] += x
            d[t] -= x
        return all(s <= capacity for s in accumulate(d))
