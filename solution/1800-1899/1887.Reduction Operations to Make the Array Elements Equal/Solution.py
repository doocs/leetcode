class Solution:
    def reductionOperations(self, nums: List[int]) -> int:
        nums.sort()
        ans = cnt = 0
        for a, b in pairwise(nums):
            if a != b:
                cnt += 1
            ans += cnt
        return ans
