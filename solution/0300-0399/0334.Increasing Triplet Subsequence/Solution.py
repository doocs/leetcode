class Solution:
    def increasingTriplet(self, nums: List[int]) -> bool:
        mi, mid = inf, inf
        for num in nums:
            if num > mid:
                return True
            if num <= mi:
                mi = num
            else:
                mid = num
        return False
