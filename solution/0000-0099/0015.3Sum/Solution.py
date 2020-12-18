class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        if nums is None or len(nums) < 3:
            return []
        nums.sort()
        n = len(nums)
        res = []
        for i in range(n - 2):
            if i > 0 and nums[i] == nums[i - 1]:
                continue
            p, q = i + 1, n - 1
            while p < q:
                if p > i + 1 and nums[p] == nums[p - 1]:
                    p += 1
                    continue
                if q < n - 1 and nums[q] == nums[q + 1]:
                    q -= 1
                    continue
                if nums[i] + nums[p] + nums[q] < 0:
                    p += 1
                elif nums[i] + nums[p] + nums[q] > 0:
                    q -= 1
                else:
                    res.append([nums[i], nums[p], nums[q]])
                    p += 1
                    q -= 1
        return res
