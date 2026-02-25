class Solution:
    def lastVisitedIntegers(self, nums: List[int]) -> List[int]:
        seen = []
        ans = []
        k = 0
        for x in nums:
            if x == -1:
                k += 1
                ans.append(-1 if k > len(seen) else seen[-k])
            else:
                k = 0
                seen.append(x)
        return ans
