class Solution:
    def stableMountains(self, height: list[int], threshold: int) -> list[int]:
        return [i for i in range(1, len(height)) if height[i - 1] > threshold]
