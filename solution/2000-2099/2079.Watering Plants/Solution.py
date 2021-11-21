class Solution:
    def wateringPlants(self, plants: List[int], capacity: int) -> int:
        ans, cap = 0, capacity
        for i, x in enumerate(plants):
            if cap >= x:
                cap -= x
                ans += 1
            else:
                cap = capacity - x
                ans += i * 2 + 1
        return ans
