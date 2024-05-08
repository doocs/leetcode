class Solution:
    def wateringPlants(self, plants: List[int], capacity: int) -> int:
        ans, water = 0, capacity
        for i, p in enumerate(plants):
            if water >= p:
                water -= p
                ans += 1
            else:
                water = capacity - p
                ans += i * 2 + 1
        return ans
