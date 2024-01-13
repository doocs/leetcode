class Solution:
    def smallestDivisor(self, nums: List[int], threshold: int) -> int:
        def f(v: int) -> bool:
            v += 1
            return sum((x + v - 1) // v for x in nums) <= threshold

        return bisect_left(range(max(nums)), True, key=f) + 1
