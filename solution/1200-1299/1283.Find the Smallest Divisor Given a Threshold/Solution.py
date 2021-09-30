class Solution:
    def smallestDivisor(self, nums: List[int], threshold: int) -> int:
        left, right = 1, 1000000
        while left < right:
            mid = (left + right) >> 1
            s = 0
            for num in nums:
                s += (num + mid - 1) // mid
            if s <= threshold:
                right = mid
            else:
                left = mid + 1
        return left
