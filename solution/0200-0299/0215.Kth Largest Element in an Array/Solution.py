class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        def quickSort(nums, left, right, k):
            if left == right:
                return nums[left]
            i, j = left - 1, right + 1
            x = nums[(left + right) >> 1]
            while i < j:
                while 1:
                    i += 1
                    if nums[i] >= x:
                        break
                while 1:
                    j -= 1
                    if nums[j] <= x:
                        break
                if i < j:
                    nums[i], nums[j] = nums[j], nums[i]
            if j < k:
                return quickSort(nums, j + 1, right, k)
            return quickSort(nums, left, j, k)

        n = len(nums)
        return quickSort(nums, 0, n - 1, n - k)
