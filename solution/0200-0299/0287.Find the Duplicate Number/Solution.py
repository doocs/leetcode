class Solution:
    def findDuplicate(self, nums: List[int]) -> int:
        l, r = 0, len(nums) - 1
        while l < r:
            mid = (l + r) >> 1
            cnt = 0
            for e in nums:
                if e <= mid:
                    cnt += 1
            if cnt <= mid:
                l = mid + 1
            else:
                r = mid
        return l
