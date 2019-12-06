'''
Given a sorted (in ascending order) integer array nums of n elements and a target value, write a function to search target in nums. If target exists, then return its index, otherwise return -1.
'''

# Perfomance
'''
Runtime: 268 ms, faster than 84.25% of Python3 online submissions for Binary Search.
Memory Usage: 14 MB, less than 100.00% of Python3 online submissions for Binary Search.
'''


class Solution:
    def search(self, nums: List[int], target: int) -> int:
        low, high = 0, len(nums) - 1
        while low <= high:
            mid = low + (high - low) // 2
            if nums[mid] == target:
                return mid
            elif nums[mid] < target:
                low = mid + 1
            else:
                high = mid - 1
        return -1
