class Solution:
    def maxScoreIndices(self, nums: List[int]) -> List[int]:
        left, right = 0, sum(nums)
        mx = right
        ans = [0]
        for i, num in enumerate(nums):
            if num == 0:
                left += 1
            else:
                right -= 1
            t = left + right
            if mx == t:
                ans.append(i + 1)
            elif mx < t:
                mx = t
                ans = [i + 1]
        return ans
