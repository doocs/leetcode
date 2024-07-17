class Solution:
    def maxScoreIndices(self, nums: List[int]) -> List[int]:
        l0, r1 = 0, sum(nums)
        mx = r1
        ans = [0]
        for i, x in enumerate(nums, 1):
            l0 += x ^ 1
            r1 -= x
            t = l0 + r1
            if mx == t:
                ans.append(i)
            elif mx < t:
                mx = t
                ans = [i]
        return ans
