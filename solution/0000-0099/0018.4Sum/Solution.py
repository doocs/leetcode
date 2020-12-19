class Solution:
    def fourSum(self, nums: List[int], target: int) -> List[List[int]]:
        res = []
        if nums is None or len(nums) < 4:
            return res
        n = len(nums)
        nums.sort()
        for i in range(n - 3):
            if i > 0 and nums[i] == nums[i - 1]:
                continue
            for j in range(i + 1, n - 2):
                if j > i + 1 and nums[j] == nums[j - 1]:
                    continue
                p, q = j + 1, n - 1
                while p < q:
                    if p > j + 1 and nums[p] == nums[p - 1]:
                        p += 1
                        continue
                    if q < n - 1 and nums[q] == nums[q + 1]:
                        q -= 1
                        continue
                    t = nums[i] + nums[j] + nums[p] + nums[q]
                    if t == target:
                        res.append([nums[i], nums[j], nums[p], nums[q]])
                        p += 1
                        q -= 1
                    elif t < target:
                        p += 1
                    else:
                        q -= 1
        return res