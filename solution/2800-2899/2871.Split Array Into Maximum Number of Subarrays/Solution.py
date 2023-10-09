class Solution:
    def maxSubarrays(self, nums: List[int]) -> int:
        score, ans = -1, 1
        for num in nums:
            score &= num
            if score == 0:
                score = -1
                ans += 1
        return 1 if ans == 1 else ans - 1
