class Solution:
    def search(self, nums: List[int], target: int) -> int:
        l, r = 0, len(nums) - 1
        while l <= r:
            mid = (l + r) >> 1
            if nums[mid] == target:
                return mid
            if nums[mid] > target:
                if nums[mid] >= nums[r] and target < nums[l]:
                    l = mid + 1
                else:
                    r = mid - 1
            else:
                if nums[mid] <= nums[l] and target > nums[r]:
                    r = mid - 1
                else:
                    l = mid + 1
        return -1
