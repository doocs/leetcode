class Solution:
    def smallestDivisor(self, nums: List[int], threshold: int) -> int:
        left, right = 1, 10**6
        while left < right:
            mid = (left + right) >> 1
            s = sum((v + mid - 1) // mid for v in nums)
            if s <= threshold:
                right = mid
            else:
                left = mid + 1
        return left
