class Solution:
    def minimizeMaxDifference(self, nums: List[int], k: int) -> int:
        nums.sort()
        l, r = 0, nums[-1] - nums[0]

        def can_minimize(target):
            ops = 0
            for i in range(1, len(nums)):
                if nums[i] - nums[i - 1] > target:
                    ops += (nums[i] - nums[i - 1] - 1) // target
                    if ops > k:
                        return False
            return True

        while l < r:
            mid = (l + r) // 2
            if can_minimize(mid):
                r = mid
            else:
                l = mid + 1
        return l
