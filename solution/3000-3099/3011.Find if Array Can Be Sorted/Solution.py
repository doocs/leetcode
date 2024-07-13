class Solution:
    def canSortArray(self, nums: List[int]) -> bool:
        pre_mx = 0
        i, n = 0, len(nums)
        while i < n:
            cnt = nums[i].bit_count()
            j = i + 1
            mi = mx = nums[i]
            while j < n and nums[j].bit_count() == cnt:
                mi = min(mi, nums[j])
                mx = max(mx, nums[j])
                j += 1
            if pre_mx > mi:
                return False
            pre_mx = mx
            i = j
        return True
