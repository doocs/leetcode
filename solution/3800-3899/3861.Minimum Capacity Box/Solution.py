class Solution:
    def minimumIndex(self, capacity: list[int], itemSize: int) -> int:
        ans = -1
        for i, x in enumerate(capacity):
            if x >= itemSize and (ans == -1 or x < capacity[ans]):
                ans = i
        return ans
