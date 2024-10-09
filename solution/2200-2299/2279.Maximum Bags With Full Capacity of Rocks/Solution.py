class Solution:
    def maximumBags(
        self, capacity: List[int], rocks: List[int], additionalRocks: int
    ) -> int:
        for i, x in enumerate(rocks):
            capacity[i] -= x
        capacity.sort()
        for i, x in enumerate(capacity):
            additionalRocks -= x
            if additionalRocks < 0:
                return i
        return len(capacity)
