class Solution:
    def search(self, nums: List[int], target: int) -> int:
        if not nums:
            return 0
        l, r = 0, len(nums) - 1
        while l <= r:
            m = l + ((r - l) >> 1)
            if nums[m] == target:
                return self._count(nums, l, r, m)
            if nums[m] < target:
                l = m + 1
            else:
                r = m - 1
        return 0
    
    def _count(self, nums, l, r, m) -> int:
        cnt = 0
        for i in range(m, l - 1, -1):
            if nums[i] == nums[m]:
                cnt += 1
            elif nums[i] < nums[m]:
                break
        
        for i in range(m + 1, r + 1):
            if nums[i] == nums[m]:
                cnt += 1
            elif nums[i] > nums[m]:
                break
        return cnt