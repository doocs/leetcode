class Solution:
    def reversePairs(self, nums: List[int]) -> int:
        def merge_sort(nums, left, right):
            if left >= right:
                return 0
            mid = (left + right) >> 1
            res = merge_sort(nums, left, mid) + merge_sort(nums, mid + 1, right)
            i, j = left, mid + 1
            tmp = []
            while i <= mid and j <= right:
                if nums[i] <= nums[j]:
                    tmp.append(nums[i])
                    i += 1
                else:
                    res += mid - i + 1
                    tmp.append(nums[j])
                    j += 1
            while i <= mid:
                tmp.append(nums[i])
                i += 1
            while j <= right:
                tmp.append(nums[j])
                j += 1
            for i in range(left, right + 1):
                nums[i] = tmp[i - left]
            return res

        return merge_sort(nums, 0, len(nums) - 1)
