class Solution:
    def canTransform(self, source: list[int], target: list[int]) -> bool:
        return sum(source) == sum(target)
