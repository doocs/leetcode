class Solution:
    def numOfBurgers(self, tomatoSlices: int, cheeseSlices: int) -> List[int]:
        k = 4 * cheeseSlices - tomatoSlices
        y = k // 2
        x = cheeseSlices - y
        return [] if k % 2 or y < 0 or x < 0 else [x, y]
