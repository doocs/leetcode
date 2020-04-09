class Solution:
    def exchange(self, nums: List[int]) -> List[int]:
        res = [0 for _ in range(len(nums))]
        p, q = 0, len(nums) - 1
        for e in nums:
            if (e & 1) == 0:
                res[q] = e
                q -= 1
            else:
                res[p] = e
                p += 1
        return res